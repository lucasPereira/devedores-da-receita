package br.lucasPereira.devedoresDaReceita.modelo;

import java.io.Serializable;
import java.util.List;

public class Devedor implements Serializable {

	private static final long serialVersionUID = 9136874723953432670L;

	private String cpf;
	private String nome;
	private String valor;
	private List<Imovel> imoveis;

	public Devedor(String cpf, String nome, String valor) {
		this.cpf = cpf;
		this.nome = nome;
		this.valor = valor;
	}

	public Devedor(Devedor devedor, List<Imovel> imoveis) {
		this.cpf = devedor.cpf;
		this.nome = devedor.nome;
		this.valor = devedor.valor;
		this.imoveis = imoveis;
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

	public String obterCpfOuCnpjComApenasNumeros() {
		return cpf.replaceAll("[.]", "").replaceAll("-", "").replaceAll("/", "");
	}

	public List<Imovel> obterImoveis() {
		return imoveis;
	}

}
