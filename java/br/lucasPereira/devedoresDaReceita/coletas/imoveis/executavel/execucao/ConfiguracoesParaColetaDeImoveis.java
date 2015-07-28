package br.lucasPereira.devedoresDaReceita.coletas.imoveis.executavel.execucao;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class ConfiguracoesParaColetaDeImoveis {

	public String obterUriInicial() {
//		return "https://sncr.serpro.gov.br";
		return "file:///home/lucas/projetos/devedoresDaReceita/html/inicio.html";
	}

	public String obterUriDeBuscaDeImoveis() {
//		return "http://sncr.serpro.gov.br/sncr-web/private/pages/consultarDeclaracao.jsf";
		return "file:///home/lucas/projetos/devedoresDaReceita/html/inicio.html";
	}

	public Integer obterTempoEmSegundosDeEspoeraParaAutenticacao() {
		return 5;
	}

	public Integer obterTempoMaximoDeEsperaDaPaginaEmSegundos() {
		return 10;
	}

	public String obterIdentificadorDaAutenticacao() {
		return "username";
	}

	public String obterUsuarioAutenticado() {
		return "Emerson Luis Schmidt";
	}

	public List<Devedor> obterDevedoresParaColetar() {
		return new LinkedList<>(Arrays.asList(new Devedor("1", "2", "3"), new Devedor("A", "B", "C")));
	}

	public String obterIdentificadorDoSeletorSr() {
		return "formConsultarDeclaracao:select-sr";
	}

	public String obterValorDoSr() {
		return "11";
	}

	public String obterIdentificadorDoBotaoPesquisar() {
		return "formConsultarDeclaracao:btFiltrar";
	}

	public String obterIdentificadorDoCampoCpfCnpj() {
		return "formConsultarDeclaracao:input-filtro-cpf-cnpj-titular";
	}

	public String obterIdentificadorDoCarregamentoDaBuscaDoDevedor() {
		return "formConsultarDeclaracao";
	}

	public String obterIdentificadorDoCarregamentoDaProximaPagina() {
		return "formConsultarDeclaracao";
	}

	public String obterIdentificadorDoCorpoTabelaImoveis() {
		return "formConsultarDeclaracao:lista-declaracoes_data";
	}

	public String obterIdentificadorDaPaginacao() {
		return "formConsultarDeclaracao:lista-declaracoes_paginator_bottom";
	}

	public String obterClasseBotaoProximaPagina() {
		return "ui-paginator-next";
	}

	public String obterClasseDoBotaoProximaPaginaDesabilitado() {
		return "ui-state-disabled";
	}

	public String obterArquivoCsvDeColetaDeImoveis() {
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatoDeData = new SimpleDateFormat("Y-MM-dd");
		SimpleDateFormat formatoDeHora = new SimpleDateFormat("HH:mm");
		Date agora = calendario.getTime();
		return String.format("csv/coleta_imoveis_%s_%s.csv", formatoDeData.format(agora), formatoDeHora.format(agora));
	}

	public String obterArquivoDeColetaDeImoveis() {
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatoDeData = new SimpleDateFormat("Y-MM-dd");
		SimpleDateFormat formatoDeHora = new SimpleDateFormat("HH:mm");
		Date agora = calendario.getTime();
		return String.format("ser/coleta_imoveis_%s_%s.ser", formatoDeData.format(agora), formatoDeHora.format(agora));
	}

}
