package br.lucasPereira.devedoresDaReceita.coletas.devedores.execucao;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.EscritorDeColetaDeDevedoresCsv;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.EscritorDeColetaDeDevedoresSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.Dorminhoco;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class ColetorDeDevedores {

	public static void main(String[] argumentos) {
		new ColetorDeDevedores();
	}

	private FirefoxDriver selenium;
	private String faixaDeValores;
	private List<Devedor> devedores;
	private ConfiguracoesDeColetaDeDevedores configuracoes;

	public ColetorDeDevedores() {
		devedores = new LinkedList<>();
		configuracoes = new ConfiguracoesDeColetaDeDevedores();
		faixaDeValores = configuracoes.obterValorDaFaixaDeValoresAcimaDeDezMilhoes();
		acessarPagina();
		buscar();
		coletar();
		persistirColetaSer();
		persistirColetaCsv();
		fecharPagina();
	}

	private void buscar() {
		System.out.println("Iniciando busca.");
		do {
			digitarCaptcha();
			buscarDevedores();
		} while (digitouCaptchaIncorretamente());
	}

	private void coletar() {
		System.out.println("Iniciando coleta de devedores.");
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
		WebElement radioFaixaDeValores = selenium.findElement(seletorRadioFaixaDeValores);
		WebElement selecaoFaixaDeValores = selenium.findElement(seletorSelecaoFaixaDeValores);
		WebElement botaoConsultar = selenium.findElement(selecaoBotaoConsultar);
		Select selecao = new Select(selecaoFaixaDeValores);
		radioFaixaDeValores.click();
		selecao.selectByValue(faixaDeValores);
		botaoConsultar.click();
		aguardaPainelDeEsperaSumir();
	}

	private Boolean existemDevedores() {
		System.out.println("Verificando se existem mais páginas de devedores.");
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
		System.out.println("Coletando devedores da página.");
		By seletorCorpoTabelaDevedores = By.id(configuracoes.obterIdentificadorDoCorpoTabelaDevedores());
		WebElement corpoTabelaDevedores = selenium.findElement(seletorCorpoTabelaDevedores);
		List<WebElement> linhasDevedores = corpoTabelaDevedores.findElements(By.tagName("tr"));
		for (WebElement linhaDevedor : linhasDevedores) {
			List<WebElement> colunasDevedor = linhaDevedor.findElements(By.tagName("td"));
			String cpf = colunasDevedor.get(0).getText();
			String nome = colunasDevedor.get(1).getText();
			String valor = colunasDevedor.get(2).getText();
			devedores.add(new Devedor(cpf, nome, valor));
		}
	}

	private void persistirColetaSer() {
		System.out.println("Persistindo coleta.");
		ColetaDeDevedores coleta = new ColetaDeDevedores(faixaDeValores, devedores);
		EscritorDeColetaDeDevedoresSer escritor = new EscritorDeColetaDeDevedoresSer();
		escritor.salvar(coleta);
	}

	private void persistirColetaCsv() {
		System.out.println("Persistindo devedores.");
		EscritorDeColetaDeDevedoresCsv escritor = new EscritorDeColetaDeDevedoresCsv();
		escritor.salvar(devedores);
	}

	private void fecharPagina() {
		System.out.println("Fechando página.");
		new Dorminhoco().descansarSegundos(30);
		selenium.close();
	}

	private void aguardaPainelDeEsperaSumir() {
		System.out.println("Aguardando carragamento concluir.");
		By seletorPainelDeEspera = By.id(configuracoes.obterIdentificadorDoPainelDeEspera());
		ExpectedCondition<Boolean> condicaoAguardarPainelDeEsperaSumir = ExpectedConditions.invisibilityOfElementLocated(seletorPainelDeEspera);
		WebDriverWait seleniumQueAguardaCondicao = new WebDriverWait(selenium, configuracoes.obterTempoMaximoDeEsperaDaPaginaEmSegundos());
		seleniumQueAguardaCondicao.until(condicaoAguardarPainelDeEsperaSumir);
	}

}
