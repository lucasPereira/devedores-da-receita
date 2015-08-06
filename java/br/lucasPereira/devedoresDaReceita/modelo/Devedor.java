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

	public String obterNome() {
		return nome;
	}

	public List<Imovel> obterImoveis() {
		return imoveis;
	}

	public String obterValorDaDivida() {
		return valor;
	}

	public String obterValorDaDividaDecimal() {
		return valor.replaceAll("[^0-9,]", "");
	}

	public String obterIdentificador() {
		return cpf;
	}

	public String obterIdentificadorApenasComNumeros() {
		return cpf.replaceAll("[^0-9]", "");
	}

}
