package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import java.util.LinkedList;
import java.util.List;

public class ContrutorDeEntradaDeLogDeDevedores {

	private Integer quantidadeDeDevedores;
	private List<Integer> quantidadesDeColunas;
	private Integer pagina;

	public ContrutorDeEntradaDeLogDeDevedores() {
		quantidadeDeDevedores = 0;
		quantidadesDeColunas = new LinkedList<>();
	}

	public void comDevedores(Integer quantidadeDeDevedores) {
		this.quantidadeDeDevedores = quantidadeDeDevedores;

	}

	public void comColunas(Integer quantidadeDeColunas) {
		quantidadesDeColunas.add(quantidadeDeColunas);
	}

	public void naPagina(Integer pagina) {
		this.pagina = pagina;

	}

	public EntradaDeLogDeDevedores construir() {
		return new EntradaDeLogDeDevedores(pagina, quantidadeDeDevedores, quantidadesDeColunas);
	}

}
