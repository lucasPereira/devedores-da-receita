package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import java.io.Serializable;
import java.util.List;

import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class ColetaDeImoveis implements Serializable {

	private static final long serialVersionUID = -4320814377596054083L;

	private List<Devedor> devedores;
	private List<Devedor> devedoresComImoveisColetados;

	public ColetaDeImoveis(List<Devedor> devedores, List<Devedor> devedoresComImoveisColetados) {
		this.devedores = devedores;
		this.devedoresComImoveisColetados = devedoresComImoveisColetados;
	}

	public List<Devedor> obterDevedores() {
		return devedores;
	}

	public List<Devedor> obterDevedoresComImoveisColetados() {
		return devedoresComImoveisColetados;
	}

}
