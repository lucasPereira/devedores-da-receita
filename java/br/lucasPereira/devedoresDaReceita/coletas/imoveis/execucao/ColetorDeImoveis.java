package br.lucasPereira.devedoresDaReceita.coletas.imoveis.execucao;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.LeitorDeColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.ColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.EscritorDeColetaDeImoveisCsv;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.EscritorDeColetaDeImoveisSer;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.LeitorDeColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.infraestrutura.Dorminhoco;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorConcreto;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorCsv;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorDataHorario;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorIdentificador;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorSer;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;
import br.lucasPereira.devedoresDaReceita.modelo.Imovel;

public class ColetorDeImoveis {

	public static void main(String[] args) {
		new ColetorDeImoveis();
	}

	private FirefoxDriver selenium;
	private List<Devedor> devedores;
	private List<Devedor> devedoresComImoveisColetados;
	private ConfiguracoesParaColetaDeImoveis configuracoes;
	private Dorminhoco dorminhoco;
	private String nomeDaColetaDeDevedores;
	private Integer primeiroIndice;
	private Boolean continuarBuscando;

	public ColetorDeImoveis() {
		JFrame janela = new JFrame("Coleta de imóveis de devedores da receita");
		JPanel painelGeral = new JPanel();
		JButton botaoFechar = new JButton("Parar coleta [?]");
		JLabel textoFechar = new JLabel("Aguarde alguns segundos até o programa salvar as consultas e ser encerrado.");
		botaoFechar.setToolTipText("Ao parar a coleta as consultas serão salvas e o programa será encerrado. Quando o programa for iniciado novamente a coleta retomará do ponto onde parou.");
		painelGeral.add(botaoFechar);
		painelGeral.add(textoFechar);
		textoFechar.setVisible(false);
		painelGeral.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 25));
		janela.add(painelGeral);
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.pack();
		janela.setAlwaysOnTop(true);
		janela.setLocation(0, 0);
		janela.setVisible(true);
		botaoFechar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evento) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						textoFechar.setVisible(true);
						janela.pack();
					}

				}).start();
				continuarBuscando = false;
			}

		});
		try {
			continuarBuscando = true;
			dorminhoco = new Dorminhoco();
			devedoresComImoveisColetados = new LinkedList<>();
			configuracoes = new ConfiguracoesParaColetaDeImoveis();
			obterColeta();
			acessarPagina();
			autenticar();
			coletar();
		} catch (Exception excecao) {
			excecao.printStackTrace();
			JOptionPane.showMessageDialog(null, String.format("Ocorreu um erro. As consultas já realizadas serão salvas e o sistema será encerrado.\n\n%s", excecao.getMessage()));
		} finally {
			if (devedoresComImoveisColetados.size() > 0) {
				try {
					persistirColetaCsv();
					persistirColetaSer();
				} catch (Exception excecao) {
					excecao.printStackTrace();
					JOptionPane.showMessageDialog(null, String.format("Ocorreu um erro grave. Não tente executar novamente o programa.\n\n%s", excecao.getMessage()));
				}
			}
			try {
				fecharPagina();
			} catch (Exception excecao) {
				excecao.printStackTrace();
			} finally {
				System.exit(0);
			}
		}
	}

	private void obterColeta() {
		nomeDaColetaDeDevedores = configuracoes.obterNomeDaColetaDeDevedores();
		String nomeDaColetaDeImoveis = configuracoes.obterNomeDaColetaDeImoveis();
		ColetaDeDevedores coletaDeDevedores = new LeitorDeColetaDeDevedores(nomeDaColetaDeDevedores).carregar();
		ColetaDeImoveis coletaDeImoveis = new LeitorDeColetaDeImoveis(nomeDaColetaDeImoveis).carregar();
		List<Devedor> todosDevedores = coletaDeDevedores.obterDevedores();
		Integer quantidadeTotalDeDevedores = todosDevedores.size();
		primeiroIndice = coletaDeImoveis.obterUltimoIndiceDaColetaDeDevedores();
		if (primeiroIndice >= 0 && primeiroIndice < quantidadeTotalDeDevedores) {
			devedores = todosDevedores.subList(primeiroIndice, quantidadeTotalDeDevedores);
		} else {
			JOptionPane.showMessageDialog(null, "A coleta já foi realizada para todos os devedores. Encerrando sistema.");
			System.exit(0);
		}
	}

	private void acessarPagina() {
		System.out.println("Acessando página.");
		selenium = new FirefoxDriver();
		selenium.get(configuracoes.obterUriInicial());
	}

	private void autenticar() {
		System.out.println("Aguardando autenticação manual.");
		do {
			Integer descanso = configuracoes.obterTempoDeDescansoEntreVerificacoesDeAutenticacao();
			dorminhoco.descansarSegundos(descanso);
		} while (!estaAutenticado() && continuarBuscando);
	}

	private Boolean estaAutenticado() {
		System.out.println("Verificando se está autenticado.");
		List<WebElement> autenticacao = selenium.findElements(configuracoes.obterSeletorDaAutenticacao());
		return !autenticacao.isEmpty() && autenticacao.get(0).isDisplayed() && autenticacao.get(0).getText().contains(configuracoes.obterUsuarioAutenticado());
	}

	private void coletar() {
		System.out.println("Iniciando coleta.");
		Iterator<Devedor> iterador = devedores.iterator();
		while (iterador.hasNext() && continuarBuscando) {
			Devedor devedor = iterador.next();
			buscarImoveis(devedor);
			coletarImoveis(devedor);
		}
	}

	private void buscarImoveis(Devedor devedor) {
		System.out.println("Buscando imóveis do devedor.");
		selenium.get(configuracoes.obterUriDeBuscaDeImoveis());
		dorminhoco.descansarSegundos(configuracoes.obterTempoDeDescansoAposIrParaPaginaDeBusca());
		new Select(selenium.findElement(configuracoes.obterSeletorDoSeletorSr())).selectByIndex(configuracoes.obterIdiceDaOpcaoSr());
		selenium.findElement(configuracoes.obterSeletorDoCampoCpfCnpj()).sendKeys(devedor.obterIdentificadorApenasComNumeros());
		selenium.findElement(configuracoes.obterSeletorDoBotaoPesquisar()).click();
		aguardarCarregamentoDaBuscaDoDevedorConcluir();
		dorminhoco.descansarSegundos(configuracoes.obterTempoDeDescansoAposBuscar());
	}

	private void coletarImoveis(Devedor devedor) {
		System.out.println("Coletando imóveis do devedor.");
		List<Imovel> imoveis = new LinkedList<>();
		if (existemImoveis()) {
			do {
				WebElement corpoTabelaImoveis = selenium.findElement(configuracoes.obterSeletorDoCorpoTabelaImoveis());
				List<WebElement> linhasImoveis = corpoTabelaImoveis.findElements(By.tagName("tr"));
				for (WebElement linhaImovel : linhasImoveis) {
					List<WebElement> colunasImovel = linhaImovel.findElements(By.tagName("td"));
					String codigo = colunasImovel.get(0).getText();
					String nome = colunasImovel.get(1).getText();
					String area = colunasImovel.get(2).getText();
					String cidade = colunasImovel.get(3).getText();
					String estado = colunasImovel.get(4).getText();
					String dataDeEntrega = colunasImovel.get(5).getText();
					String situacaoDaDeclaracao = colunasImovel.get(6).getText();
					Imovel imovel = new Imovel(devedor, codigo, nome, area, cidade, estado, dataDeEntrega, situacaoDaDeclaracao);
					imoveis.add(imovel);
				}
			} while (irParaProximaPagina());
		}
		Devedor devedorComImoveisColetados = new Devedor(devedor, imoveis);
		devedoresComImoveisColetados.add(devedorComImoveisColetados);
	}

	private Boolean existemImoveis() {
		System.out.println("Verificando se existem imóveis para o devedor");
		try {
			return selenium.findElement(configuracoes.obterSeletorDoResultadoDeImoveis()).isDisplayed();
		} catch (NoSuchElementException excecao) {
			return false;
		}
	}

	private Boolean irParaProximaPagina() {
		System.out.println("Verificando se existem mais páginas de imóveis do devedor");
		WebElement paginacao = selenium.findElement(configuracoes.obterSeletorDaPaginacao());
		WebElement botaoProximaPagina = paginacao.findElement(configuracoes.obterSeletorDoBotaoProximaPagina());
		Boolean botaoProximaPaginaEstaDesabilitado = botaoProximaPagina.getAttribute("class").contains(configuracoes.obterClasseDoBotaoProximaPaginaDesabilitado());
		if (!botaoProximaPaginaEstaDesabilitado) {
			botaoProximaPagina.click();
			aguardarCarregamentoDaProximaPaginaConcluir();
			dorminhoco.descansarSegundos(configuracoes.obterTempoDeDescansoAposIrParaProximaPagina());
			return true;
		}
		return false;
	}

	private void aguardarCarregamentoDaBuscaDoDevedorConcluir() {
		System.out.println("Aguardando carragamento da busca dos imovéis do devedor concluir.");
		ExpectedCondition<Boolean> condicaoAguardarTerminoDoCarregamento = ExpectedConditions.invisibilityOfElementLocated(configuracoes.obterSeletorDeCarregamento());
		WebDriverWait seleniumQueAguardaCondicao = new WebDriverWait(selenium, configuracoes.obterTempoMaximoDeEsperaDaPaginaEmSegundos());
		seleniumQueAguardaCondicao.until(condicaoAguardarTerminoDoCarregamento);
	}

	private void aguardarCarregamentoDaProximaPaginaConcluir() {
		System.out.println("Aguardando carragamento da próxima página concluir.");
		ExpectedCondition<Boolean> condicaoAguardarTerminoDoCarregamento = ExpectedConditions.invisibilityOfElementLocated(configuracoes.obterSeletorDeCarregamento());
		WebDriverWait seleniumQueAguardaCondicao = new WebDriverWait(selenium, configuracoes.obterTempoMaximoDeEsperaDaPaginaEmSegundos());
		seleniumQueAguardaCondicao.until(condicaoAguardarTerminoDoCarregamento);
	}

	private void persistirColetaCsv() {
		System.out.println("Persistindo imóveis.");
		NomeadorDataHorario nomeador = new NomeadorDataHorario(new NomeadorCsv(new NomeadorConcreto()), "imoveis");
		EscritorDeColetaDeImoveisCsv escritor = new EscritorDeColetaDeImoveisCsv(nomeador);
		escritor.salvar(devedoresComImoveisColetados);
	}

	private void persistirColetaSer() {
		System.out.println("Persistindo coleta.");
		ColetaDeImoveis coleta = new ColetaDeImoveis(nomeDaColetaDeDevedores, primeiroIndice, primeiroIndice + devedoresComImoveisColetados.size(), devedoresComImoveisColetados);
		new EscritorDeColetaDeImoveisSer(new NomeadorDataHorario(new NomeadorSer(new NomeadorConcreto()), "imoveis")).salvar(coleta);
		new EscritorDeColetaDeImoveisSer(new NomeadorIdentificador(new NomeadorSer(new NomeadorConcreto()), "imoveis")).salvar(coleta);
	}

	private void fecharPagina() {
		System.out.println("Fechando página.");
		WebElement botaoSair = selenium.findElement(configuracoes.obterSeletorDoBotaoDeSair());
		botaoSair.click();
		dorminhoco.descansarSegundos(configuracoes.obterTempoDeDescansoAntesDeFechar());
		selenium.close();
	}

}
