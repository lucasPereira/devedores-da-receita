package br.lucasPereira.devedoresDaReceita;

import java.util.LinkedList;
import java.util.List;

public class ContrutorDeEntradaDeLog {

	private Integer quantidadeDeDevedores;
	private List<Integer> quantidadesDeColunas;
	private Integer pagina;

	public ContrutorDeEntradaDeLog() {
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

	public EntradaDeLog construir() {
		return new EntradaDeLog(pagina, quantidadeDeDevedores, quantidadesDeColunas);
	}

}
