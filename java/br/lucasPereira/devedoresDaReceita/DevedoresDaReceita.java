package br.lucasPereira.devedoresDaReceita;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevedoresDaReceita {

	public static void main(String[] argumentos) {
		new DevedoresDaReceita();
	}

	private Log log;
	private String faixaDeValores;
	private FirefoxDriver selenium;
	private List<Devedor> devedores;
	private Configuracoes configuracoes;

	public DevedoresDaReceita() {
		log = new Log();
		devedores = new LinkedList<>();
		configuracoes = new Configuracoes();
		faixaDeValores = configuracoes.obterValorDaFaixaDeValoresAcimaDeDezMilhoes();
		acessarPagina();
		buscar();
		coletar();
		persistirColeta();
		persistirDevedores();
		persistirLog();
		fecharPagina();
	}

	private void buscar() {
		do {
			digitarCaptcha();
			buscarDevedores();
		} while (digitouCaptchaIncorretamente());
	}

	private void coletar() {
		try {
			do {
				coletarDevedores();
			} while (existemDevedores());
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
	}

	private Boolean digitouCaptchaIncorretamente() {
		System.out.println("Verificando se o captcha foi digitado corretamente.");
		By seletorPainelDeErroDoCaptcha = By.id(configuracoes.obterIdentificadorDoPainelDeErroDoCaptcha());
		WebElement painelDeErroDoCaptcha = selenium.findElement(seletorPainelDeErroDoCaptcha);
		return painelDeErroDoCaptcha.isDisplayed();
	}

	private void acessarPagina() {
		System.out.println("Acessando página.");
		selenium = new FirefoxDriver();
		selenium.get(configuracoes.obterUriDaPaginaComDevedores());
	}

	private void digitarCaptcha() {
		System.out.println("Aguardando digitação do captcha.");
		By selecaoCampoCaptcha = By.id(configuracoes.obterIdentificadorDoCampoCaptcha());
		WebElement campoCaptcha = selenium.findElement(selecaoCampoCaptcha);
		campoCaptcha.click();
		new Dorminhoco().descansarSegundos(configuracoes.obterTempoEmSegundosParaDigitacaoDoCapatcha());
	}

	private void buscarDevedores() {
		System.out.println("Buscando devedores.");
		By seletorRadioFaixaDeValores = By.id(configuracoes.obterIdentificadorDoRadioFaixaDeValores());
		By seletorSelecaoFaixaDeValores = By.id(configuracoes.obterIdentificadorDoSeletorFaixaDeValores());
		By selecaoBotaoConsultar = By.id(configuracoes.obterIdentificadorDoBotaoConsultar());
		String valorFaixaDeValoresAcimaDeDuzentosMil = faixaDeValores;
		WebElement radioFaixaDeValores = selenium.findElement(seletorRadioFaixaDeValores);
		WebElement selecaoFaixaDeValores = selenium.findElement(seletorSelecaoFaixaDeValores);
		WebElement botaoConsultar = selenium.findElement(selecaoBotaoConsultar);
		Select selecao = new Select(selecaoFaixaDeValores);
		radioFaixaDeValores.click();
		selecao.selectByValue(valorFaixaDeValoresAcimaDeDuzentosMil);
		botaoConsultar.click();
		aguardaPainelDeEsperaSumir();
	}

	private Boolean existemDevedores() {
		By seletorTabelaPaginacao = By.id(configuracoes.obterIdentificadorDaTabelaPaginacao());
		WebElement tabelaPaginacao = selenium.findElement(seletorTabelaPaginacao);
		List<WebElement> botaoProximaPagina = tabelaPaginacao.findElements(By.className(configuracoes.obterClasseBotaoProximaPagina()));
		if (!botaoProximaPagina.isEmpty()) {
			botaoProximaPagina.iterator().next().click();
			aguardaPainelDeEsperaSumir();
			return true;
		}
		return false;
	}

	private void coletarDevedores() {
		By seletorCorpoTabelaDevedores = By.id(configuracoes.obterIdentificadorDoCorpoTabelaDevedores());
		WebElement corpoTabelaDevedores = selenium.findElement(seletorCorpoTabelaDevedores);
		List<WebElement> linhasDevedores = corpoTabelaDevedores.findElements(By.tagName("tr"));
		ContrutorDeEntradaDeLog contrutorDeEntradaDeLog = new ContrutorDeEntradaDeLog();
		contrutorDeEntradaDeLog.comDevedores(linhasDevedores.size());
		for (WebElement linhaDevedor : linhasDevedores) {
			List<WebElement> colunasDevedor = linhaDevedor.findElements(By.tagName("td"));
			contrutorDeEntradaDeLog.comColunas(colunasDevedor.size());
			String cpf = colunasDevedor.get(0).getText();
			String nome = colunasDevedor.get(1).getText();
			String valor = colunasDevedor.get(2).getText();
			devedores.add(new Devedor(cpf, nome, valor));
		}
		log.adicionarEntrada(contrutorDeEntradaDeLog);
	}

	private void persistirColeta() {
		System.out.println("Persistindo coleta.");
		try {
			FileOutputStream arquivo = new FileOutputStream(configuracoes.obterArquivoDeColeta());
			ObjectOutputStream saida = new ObjectOutputStream(arquivo);
			saida.writeObject(new Coleta(faixaDeValores, devedores, log));
			saida.close();
		} catch (FileNotFoundException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

	private void persistirDevedores() {
		System.out.println("Persistindo devedores.");
		try {
			FileWriter escritor = new FileWriter(configuracoes.obterArquivoDeDevedores());
			for (Devedor devedor : devedores) {
				String cpf = devedor.obterCpf();
				String nome = devedor.obterNome();
				String valor = devedor.obterValor();
				escritor.write(String.format("%s;%s;%s", cpf, nome, valor));
				escritor.write(System.lineSeparator());
			}
			escritor.close();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

	private void persistirLog() {
		System.out.println("Imprimindo log.");
		try {
			FileWriter escritor = new FileWriter(configuracoes.obterArquivoDeLog());
			for (EntradaDeLog entradaDeLog : log) {
				Integer pagina = entradaDeLog.obterPagina();
				Integer quantidadeDeDevedores = entradaDeLog.obterQuantidadeDeDevedores();
				String quantidadesDeColunas = entradaDeLog.obterQuantidadesDeColunas().toString();
				escritor.write(String.format("%d;%d;%s", pagina, quantidadeDeDevedores, quantidadesDeColunas));
				escritor.write(System.lineSeparator());
			}
			escritor.close();
		} catch (IOException excecao) {
			excecao.printStackTrace();
			excecao.printStackTrace();
		}
	}

	private void fecharPagina() {
		System.out.println("Fechando página.");
		new Dorminhoco().descansarSegundos(30);
		selenium.close();
	}

	private void aguardaPainelDeEsperaSumir() {
		By seletorPainelDeEspera = By.id(configuracoes.obterIdentificadorDoPainelDeEspera());
		ExpectedCondition<Boolean> condicaoAguardarPainelDeEsperaSumir = ExpectedConditions.invisibilityOfElementLocated(seletorPainelDeEspera);
		WebDriverWait seleniumQueAguardaCondicao = new WebDriverWait(selenium, configuracoes.obterTempoMaximoDeEsperaDaPaginaEmSegundos());
		seleniumQueAguardaCondicao.until(condicaoAguardarPainelDeEsperaSumir);
	}

}
