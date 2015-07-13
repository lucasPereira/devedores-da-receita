package br.lucasPereira.devedoresDaReceita;

import java.io.Serializable;

public class Devedor implements Serializable {

	private static final long serialVersionUID = 9136874723953432670L;

	private String cpf;
	private String nome;
	private String valor;

	public Devedor(String cpf, String nome, String valor) {
		this.cpf = cpf;
		this.nome = nome;
		this.valor = valor;
	}

	public String obterValor() {
		return valor;
	}

	public String obterNome() {
		return nome;
	}

	public String obterCpf() {
		return cpf;
	}

}
