package br.lucasPereira.devedoresDaReceita.coletas.imoveis.execucao;

import org.openqa.selenium.By;

public class ConfiguracoesParaColetaDeImoveis {

	private static final Boolean SIMULAR = false;

	public String obterUriInicial() {
		return SIMULAR ? "file:///home/lucas/projetos/devedoresDaReceita/html/inicio.html" : "https://sncr.serpro.gov.br";
	}

	public String obterUriDeBuscaDeImoveis() {
		return SIMULAR ? "file:///home/lucas/projetos/devedoresDaReceita/html/inicio.html" : "http://sncr.serpro.gov.br/sncr-web/private/pages/consultarDeclaracao.jsf";
	}

	public String obterNomeDaColetaDeDevedores() {
		return "ser/devedores_2015.07.27_02.56.ser";
	}

	public String obterNomeDaColetaDeImoveis() {
		return "ser/imoveis.ser";
	}

	public Integer obterQuantidadeDeDevedoresPorColeta() {
		return 10;
	}

	public Integer obterTempoEmSegundosDeEspoeraParaAutenticacao() {
		return 5;
	}

	public Integer obterTempoMaximoDeEsperaDaPaginaEmSegundos() {
		return 10;
	}

	public long obterTempoMaximoDeEsperaDoElementoEmSegundos() {
		return 10;
	}

	public Integer obterIdiceDaOpcaoSr() {
		return 0;
	}

	public String obterUsuarioAutenticado() {
		return "Emerson Luis Schmidt";
	}

	public By obterSeletorDaAutenticacao() {
		return By.id("username");
	}

	public By obterSeletorDoSeletorSr() {
		return By.id("formConsultarDeclaracao:select-sr");
	}

	public By obterSeletorDoBotaoPesquisar() {
		return By.id("formConsultarDeclaracao:btFiltrar");
	}

	public By obterSeletorDoCampoCpfCnpj() {
		return By.id("formConsultarDeclaracao:input-filtro-cpf-cnpj-titular");
	}

	public By obterSeletorDoCarregamentoDaBuscaDoDevedor() {
		return obterSeletorDeCarregamento();
	}

	public By obterSeletorDoCarregamentoDaProximaPagina() {
		return obterSeletorDeCarregamento();
	}

	public By obterSeletorDoCorpoTabelaImoveis() {
		return By.id("formConsultarDeclaracao:lista-declaracoes_data");
	}

	public By obterSeletorDaPaginacao() {
		return By.id("formConsultarDeclaracao:lista-declaracoes_paginator_bottom");
	}

	public By obterSeletorDoBotaoProximaPagina() {
		return By.className("ui-paginator-next");
	}

	public String obterClasseDoBotaoProximaPaginaDesabilitado() {
		return "ui-state-disabled";
	}

	public By obterSeletorDoBotaoDeSair() {
		return By.id("btSair");
	}

	public By obterSeletorDeCarregamento() {
		return By.cssSelector("img[src$=\"ajaxloadingbar.gif\"]");
	}

	public By obterSeletorDoResultadoDeImoveis() {
		return By.id("formConsultarDeclaracao:lista-declaracoes");
	}

}
