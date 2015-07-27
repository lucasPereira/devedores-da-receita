package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import java.io.Serializable;
import java.util.List;

import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class ColetaDeDevedores implements Serializable {

	private static final long serialVersionUID = -3524332189543598469L;

	private String faixaDeValores;
	private List<Devedor> devedores;
	private LogDeDevedores log;

	public ColetaDeDevedores(String faixaDeValores, List<Devedor> devedores, LogDeDevedores log) {
		this.faixaDeValores = faixaDeValores;
		this.devedores = devedores;
		this.log = log;
	}

	public List<Devedor> obterDevedores() {
		return devedores;
	}

	public String obterFaixaDeValores() {
		return faixaDeValores;
	}

	public LogDeDevedores obterLog() {
		return log;
	}

}
