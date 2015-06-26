package br.lucasPereira.devedoresDaReceita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DevedoresDaReceita {

	public static void main(String[] argumentos) {
		new DevedoresDaReceita();
	}

	private Configuracoes configuracoes;
	private FirefoxDriver selenium;

	public DevedoresDaReceita() {
		configuracoes = new Configuracoes();
		acessarPagina();
		digitarCaptcha();
		buscar();
		coletarDevedores();
		persistirDevedores();
		fecharPagina();
	}

	private void acessarPagina() {
		selenium = new FirefoxDriver();
		selenium.get(configuracoes.obterUriDaPaginaComDevedores());
	}

	private void digitarCaptcha() {
		By selecaoCampoCaptcha = By.id(configuracoes.obterIdentificadorDoCampoCaptcha());
		WebElement campoCaptcha = selenium.findElement(selecaoCampoCaptcha);
		campoCaptcha.click();
		new Dorminhoco().descansarSegundos(configuracoes.obterTempoEmSegundosParaDigitacaoDoCapatcha());
	}

	private void buscar() {
		By seletorRadioFaixaDeValores = By.id(configuracoes.obterIdentificadorDoRadioFaixaDeValores());
		By seletorSelecaoFaixaDeValores = By.id(configuracoes.obterIdentificadorDoSeletorFaixaDeValores());
		By selecaoBotaoConsultar = By.id(configuracoes.obterIdentificadorDoBotaoConsultar());
		String valorFaixaDeValoresAcimaDeDuzentosMil = configuracoes.obterValorDaFaixaDeValoresAcimaDeDuzentosMil();
		WebElement radioFaixaDeValores = selenium.findElement(seletorRadioFaixaDeValores);
		WebElement selecaoFaixaDeValores = selenium.findElement(seletorSelecaoFaixaDeValores);
		WebElement botaoConsultar = selenium.findElement(selecaoBotaoConsultar);
		Select selecao = new Select(selecaoFaixaDeValores);
		radioFaixaDeValores.click();
		selecao.selectByValue(valorFaixaDeValoresAcimaDeDuzentosMil);
		botaoConsultar.click();
	}

	private void coletarDevedores() {
		// TODO Auto-generated method stub

	}

	private void persistirDevedores() {
		// TODO Auto-generated method stub

	}

	private void fecharPagina() {
		new Dorminhoco().descansarSegundos(30);
		selenium.close();
	}

}
