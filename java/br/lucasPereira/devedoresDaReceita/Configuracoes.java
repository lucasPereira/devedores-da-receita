package br.lucasPereira.devedoresDaReceita;

public class Configuracoes {

	public String obterUriDaPaginaComDevedores() {
		return "https://www2.pgfn.fazenda.gov.br/ecac/contribuinte/devedores/listaDevedores.jsf;jsessionid=6FFC570043A5DD4EC6672AE4D48D6EB7.app1";
	}

	public Integer obterTempoEmSegundosParaDigitacaoDoCapatcha() {
		return 30;
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

	public String obterIdentificadorDoBotaoConsultar() {
		return "listaDevedoresForm:consultarButton";
	}

	public String obterIdentificadorDoCampoCaptcha() {
		return "listaDevedoresForm:captcha";
	}

}
