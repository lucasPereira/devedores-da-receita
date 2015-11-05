package br.lucasPereira.devedoresDaReceita.coletas.devedores.execucao;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.Nomeador;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorConcreto;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorCsv;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorDataHorario;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorSer;

public class ConfiguracoesDeColetaDeDevedores {

	public String obterUriDaPaginaComDevedores() {
		return "https://www2.pgfn.fazenda.gov.br/ecac/contribuinte/devedores/listaDevedores.jsf";
	}

	public Integer obterTempoEmSegundosParaDigitacaoDoCapatcha() {
		return 10;
	}

	public Integer obterTempoMaximoDeEsperaDaPaginaEmSegundos() {
		return 60;
	}

	public String obterIdentificadorDoRadioFaixaDeValores() {
		return "listaDevedoresForm:tipoConsultaRadio:1";
	}

	public String obterIdentificadorDoSeletorFaixaDeValores() {
		return "listaDevedoresForm:faixasInput";
	}

	public String obterValorDaFaixaDeValoresAcimaDeZero() {
		return "ATE_20_MIL";
	}

	public String obterValorDaFaixaDeValoresAcimaDeVinteMil() {
		return "DE_20_MIL_ATE_200_MIL";
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

	public String obterValorDaFaixaDeValores() {
		return obterValorDaFaixaDeValoresAcimaDeUmMilhao();
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

	public Nomeador obterNomeadorParaColetaSer() {
		return new NomeadorDataHorario(new NomeadorSer(new NomeadorConcreto()), "devedores");
	}

	public NomeadorDataHorario obterNomeadorParaColetaCsv() {
		return new NomeadorDataHorario(new NomeadorCsv(new NomeadorConcreto()), "devedores");
	}

	public Integer obterTempoDeEsperaEmSegundosAntesDeFecharAPagina() {
		return 30;
	}

	public Integer obterUltimaPaginaConsultada() {
		return 3214 + 417 + 282;
	}

}
