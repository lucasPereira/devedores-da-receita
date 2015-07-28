package br.lucasPereira.devedoresDaReceita.coletas.imoveis.executavel.execucao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import br.lucasPereira.devedoresDaReceita.coletas.imoveis.ColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.EscritorDeColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.infraestrutura.Dorminhoco;
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

	public ColetorDeImoveis() {
		configuracoes = new ConfiguracoesParaColetaDeImoveis();
		// Definir devedores
		devedores = configuracoes.obterDevedoresParaColetar();
		devedoresComImoveisColetados = new LinkedList<>();
		acessarPagina();
		autenticar();
		coletar();
		persistirColeta();
		persistirImoveis();
		fecharPagina();
	}

	private void acessarPagina() {
		System.out.println("Acessando página.");
		// Verificar URI
		selenium = new FirefoxDriver();
		selenium.get(configuracoes.obterUriInicial());
	}

	private void autenticar() {
		System.out.println("Aguardando autenticação manual.");
		do {
			new Dorminhoco().descansarSegundos(configuracoes.obterTempoEmSegundosDeEspoeraParaAutenticacao());
		} while (!estaAutenticado());
	}

	private Boolean estaAutenticado() {
		System.out.println("Verificando se está autenticado.");
		try {
			By seletorDaAutenticacao = By.id(configuracoes.obterIdentificadorDaAutenticacao());
			WebElement autenticacao = selenium.findElement(seletorDaAutenticacao);
			return autenticacao.isDisplayed() && autenticacao.getText().contains(configuracoes.obterUsuarioAutenticado());
		} catch (NoSuchElementException excecao) {
			return false;
		}
	}

	private void coletar() {
		System.out.println("Inicinado coleta.");
		try {
			for (Devedor devedor : devedores) {
				buscarImoveis(devedor);
				coletarImoveis(devedor);
			}
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
	}

	private void buscarImoveis(Devedor devedor) {
		System.out.println("Buscando imóveis do devedor.");
		// Verificar URI
		selenium.get(configuracoes.obterUriDeBuscaDeImoveis());
		// Verificar necessidade de seleção do SR
		By seletorSelecaoSr = By.id(configuracoes.obterIdentificadorDoSeletorSr());
		WebElement selecaoSr = selenium.findElement(seletorSelecaoSr);
		Select selecao = new Select(selecaoSr);
		selecao.selectByValue(configuracoes.obterValorDoSr());
		By seletorCampoCpfCnpj = By.id(configuracoes.obterIdentificadorDoCampoCpfCnpj());
		WebElement campoCpfCnpj = selenium.findElement(seletorCampoCpfCnpj);
		campoCpfCnpj.sendKeys(devedor.obterCpfOuCnpjComApenasNumeros());
		By seletorBotaoPesquisar = By.id(configuracoes.obterIdentificadorDoBotaoPesquisar());
		WebElement botaoPesquisar = selenium.findElement(seletorBotaoPesquisar);
		botaoPesquisar.click();
		aguardarCarregamentoDaBuscaDoDevedorConcluir();
	}

	private void coletarImoveis(Devedor devedor) {
		System.out.println("Coletando imóveis do devedor.");
		List<Imovel> imoveis = new LinkedList<>();
		do {
			By seletorCorpoTabelaImoveis = By.id(configuracoes.obterIdentificadorDoCorpoTabelaImoveis());
			WebElement corpoTabelaImoveis = selenium.findElement(seletorCorpoTabelaImoveis);
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
				Imovel imovel = new Imovel(codigo, nome, area, cidade, estado, dataDeEntrega, situacaoDaDeclaracao);
				imoveis.add(imovel);
			}
		} while (existemImoveis());
		Devedor devedorComImoveisColetados = new Devedor(devedor, imoveis);
		devedoresComImoveisColetados.add(devedorComImoveisColetados);
	}

	private Boolean existemImoveis() {
		System.out.println("Verificando se existem mais páginas de imóveis do devedor");
		By seletorPaginacao = By.id(configuracoes.obterIdentificadorDaPaginacao());
		WebElement paginacao = selenium.findElement(seletorPaginacao);
		WebElement botaoProximaPagina = paginacao.findElement(By.className(configuracoes.obterClasseBotaoProximaPagina()));
		if (!botaoProximaPagina.getAttribute("class").contains(configuracoes.obterClasseDoBotaoProximaPaginaDesabilitado())) {
			botaoProximaPagina.click();
			aguardarCarregamentoDaProximaPaginaConcluir();
			return true;
		}
		return false;
	}

	private void aguardarCarregamentoDaBuscaDoDevedorConcluir() {
		System.out.println("Aguardando carragamento da busca dos imovéis devedor concluir.");
		// Verificar necessidade de espera
		// Corrigir seletor de espera
//		By seletorCarregamento = By.id(configuracoes.obterIdentificadorDoCarregamentoDaBuscaDoDevedor());
//		ExpectedCondition<Boolean> condicaoAguardarTerminoDoCarregamento = ExpectedConditions.invisibilityOfElementLocated(seletorCarregamento);
//		WebDriverWait seleniumQueAguardaCondicao = new WebDriverWait(selenium, configuracoes.obterTempoMaximoDeEsperaDaPaginaEmSegundos());
//		seleniumQueAguardaCondicao.until(condicaoAguardarTerminoDoCarregamento);
	}

	private void aguardarCarregamentoDaProximaPaginaConcluir() {
		System.out.println("Aguardando carragamento da próxima página concluir.");
		// Verificar necessidade de espera
		// Corrigir seletor de espera
//		By seletorCarregamento = By.id(configuracoes.obterIdentificadorDoCarregamentoDaProximaPagina());
//		ExpectedCondition<Boolean> condicaoAguardarTerminoDoCarregamento = ExpectedConditions.invisibilityOfElementLocated(seletorCarregamento);
//		WebDriverWait seleniumQueAguardaCondicao = new WebDriverWait(selenium, configuracoes.obterTempoMaximoDeEsperaDaPaginaEmSegundos());
//		seleniumQueAguardaCondicao.until(condicaoAguardarTerminoDoCarregamento);
	}

	private void persistirColeta() {
		System.out.println("Persistindo coleta.");
		ColetaDeImoveis coleta = new ColetaDeImoveis(devedores, devedoresComImoveisColetados);
		String nomeDoArquivo = configuracoes.obterArquivoDeColetaDeImoveis();
		EscritorDeColetaDeImoveis escritor = new EscritorDeColetaDeImoveis(nomeDoArquivo);
		escritor.salvar(coleta);
	}

	private void persistirImoveis() {
		System.out.println("Persistindo imóveis.");
		try {
			FileWriter escritor = new FileWriter(configuracoes.obterArquivoCsvDeColetaDeImoveis());
			for (Devedor devedor : devedoresComImoveisColetados) {
				String cpf = devedor.obterCpf();
				String nome = devedor.obterNome();
				String valor = devedor.obterValor();
				for (Imovel imovel : devedor.obterImoveis()) {
					String codigo = imovel.obterCodigo();
					String nomeDoImovel = imovel.obterNome();
					String area = imovel.obterArea();
					String cidade = imovel.obterCidade();
					String estado = imovel.obterEstado();
					String dataDeEntrega = imovel.obterDataDeEntrega();
					String situacaoDaDeclaracao = imovel.obterSituacaoDaDeclaracao();
					escritor.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", cpf, nome, valor, codigo, nomeDoImovel, area, cidade, estado, dataDeEntrega, situacaoDaDeclaracao));
					escritor.write(System.lineSeparator());
				}
			}
			escritor.close();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

	private void fecharPagina() {
		System.out.println("Fechando página.");
		new Dorminhoco().descansarSegundos(30);
		selenium.close();
	}

}
