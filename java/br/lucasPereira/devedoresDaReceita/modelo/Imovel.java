package br.lucasPereira.devedoresDaReceita.modelo;

import java.io.Serializable;

public class Imovel implements Serializable {

	private static final long serialVersionUID = 9045389645889467102L;

	private String codigo;
	private String nome;
	private String area;
	private String cidade;
	private String estado;
	private String dataDeEntrega;
	private String situacaoDaDeclaracao;

	public Imovel(String codigo, String nome, String area, String cidade, String estado, String dataDeEntrega, String situacaoDaDeclaracao) {
		this.codigo = codigo;
		this.nome = nome;
		this.area = area;
		this.cidade = cidade;
		this.estado = estado;
		this.dataDeEntrega = dataDeEntrega;
		this.situacaoDaDeclaracao = situacaoDaDeclaracao;
	}

	public String obterCodigo() {
		return codigo;
	}

	public String obterNome() {
		return nome;
	}

	public String obterArea() {
		return area;
	}

	public String obterCidade() {
		return cidade;
	}

	public String obterEstado() {
		return estado;
	}

	public String obterDataDeEntrega() {
		return dataDeEntrega;
	}

	public String obterSituacaoDaDeclaracao() {
		return situacaoDaDeclaracao;
	}

}
