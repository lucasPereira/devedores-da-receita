package br.lucasPereira.devedoresDaReceita.infraestrutura;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Configuracoes {

	public String obterUriDaPaginaComDevedores() {
		return "https://www2.pgfn.fazenda.gov.br/ecac/contribuinte/devedores/listaDevedores.jsf;jsessionid=6FFC570043A5DD4EC6672AE4D48D6EB7.app1";
	}

	public Integer obterTempoEmSegundosParaDigitacaoDoCapatcha() {
		return 10;
	}

	public String obterIdentificadorDoRadioFaixaDeValores() {
		return "listaDevedoresForm:tipoConsultaRadio:1";
	}

	public String obterIdentificadorDoSeletorFaixaDeValores() {
		return "listaDevedoresForm:faixasInput";
	}

	public String obterValorDaFaixaDeValoresAcimaDeDuzentosMil() {
		return "DE_200_MIL_ATE_1_MILHAO";
	}

	public String obterValorDaFaixaDeValoresAcimaDeUmMilhao() {
		return "DE_1_MILHAO_ATE_10_MILHOES";
	}

	public String obterValorDaFaixaDeValoresAcimaDeDezMilhoes() {
		return "ALEM_DE_10_MILHOES";
	}

	public String obterIdentificadorDoBotaoConsultar() {
		return "listaDevedoresForm:consultarButton";
	}

	public String obterIdentificadorDoCampoCaptcha() {
		return "listaDevedoresForm:captcha";
	}

	public String obterIdentificadorDoCorpoTabelaDevedores() {
		return "listaDevedoresForm:devedoresTable:tb";
	}

	public Integer obterTempoMaximoDeEsperaDaPaginaEmSegundos() {
		return 10;
	}

	public String obterIdentificadorDoPainelDeEspera() {
		return "ajaxStatusPanelHeader";
	}

	public String obterIdentificadorDaTabelaPaginacao() {
		return "listaDevedoresForm:devedoresTableScroller_table";
	}

	public String obterClasseBotaoProximaPagina() {
		return "arrow-next";
	}

	public String obterIdentificadorDoPainelDeErroDoCaptcha() {
		return "listaDevedoresForm:captchaMessage";
	}

	public String obterArquivoDeDevedores() {
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatoDeData = new SimpleDateFormat("Y-MM-dd");
		SimpleDateFormat formatoDeHora = new SimpleDateFormat("HH:mm");
		Date agora = calendario.getTime();
		return String.format("csv/devedores_%s_%s.csv", formatoDeData.format(agora), formatoDeHora.format(agora));
	}

	public String obterArquivoDeLog() {
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatoDeData = new SimpleDateFormat("Y-MM-dd");
		SimpleDateFormat formatoDeHora = new SimpleDateFormat("HH:mm");
		Date agora = calendario.getTime();
		return String.format("csv/log_%s_%s.csv", formatoDeData.format(agora), formatoDeHora.format(agora));
	}

	public String obterArquivoDeColeta() {
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatoDeData = new SimpleDateFormat("Y-MM-dd");
		SimpleDateFormat formatoDeHora = new SimpleDateFormat("HH:mm");
		Date agora = calendario.getTime();
		return String.format("ser/coleta_%s_%s.ser", formatoDeData.format(agora), formatoDeHora.format(agora));
	}

}
