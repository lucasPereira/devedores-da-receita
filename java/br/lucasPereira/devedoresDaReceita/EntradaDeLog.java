package br.lucasPereira.devedoresDaReceita;

import java.io.Serializable;
import java.util.List;

public class EntradaDeLog implements Serializable {

	private static final long serialVersionUID = -9195925265656730987L;

	private Integer pagina;
	private Integer quantidadeDeDevedores;
	private List<Integer> quantidadesDeColunas;

	public EntradaDeLog(Integer pagina, Integer quantidadeDeDevedores, List<Integer> quantidadesDeColunas) {
		this.pagina = pagina;
		this.quantidadeDeDevedores = quantidadeDeDevedores;
		this.quantidadesDeColunas = quantidadesDeColunas;
	}

	public Integer obterPagina() {
		return pagina;
	}

	public Integer obterQuantidadeDeDevedores() {
		return quantidadeDeDevedores;
	}

	public List<Integer> obterQuantidadesDeColunas() {
		return quantidadesDeColunas;
	}

}
