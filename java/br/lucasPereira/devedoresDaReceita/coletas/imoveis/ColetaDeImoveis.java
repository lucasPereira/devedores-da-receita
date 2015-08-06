package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import java.io.Serializable;
import java.util.List;

import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class ColetaDeImoveis implements Serializable {

	private static final long serialVersionUID = -4320814377596054083L;

	private String nomeDaColetaDeDevedores;
	private Integer primeiroIndiceDaColetaDeDevedores;
	private Integer ultimoIndiceDaColetaDeDevedores;
	private List<Devedor> devedoresComImoveisColetados;

	public ColetaDeImoveis(String nomeDaColetaDeDevedores, Integer primeiroIndiceDaColetaDeDevedores, Integer ultimoIndiceDaColetaDeDevedores, List<Devedor> devedoresComImoveisColetados) {
		this.nomeDaColetaDeDevedores = nomeDaColetaDeDevedores;
		this.primeiroIndiceDaColetaDeDevedores = primeiroIndiceDaColetaDeDevedores;
		this.ultimoIndiceDaColetaDeDevedores = ultimoIndiceDaColetaDeDevedores;
		this.devedoresComImoveisColetados = devedoresComImoveisColetados;
	}

	public List<Devedor> obterDevedoresComImoveisColetados() {
		return devedoresComImoveisColetados;
	}

	public Integer obterPrimeiroIndiceDaColetaDeDevedores() {
		return primeiroIndiceDaColetaDeDevedores;
	}

	public Integer obterUltimoIndiceDaColetaDeDevedores() {
		return ultimoIndiceDaColetaDeDevedores;
	}

	public String obterNomeDaColetaDeDevedores() {
		return nomeDaColetaDeDevedores;
	}

}
