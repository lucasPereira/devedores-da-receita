package br.lucasPereira.devedoresDaReceita;

import java.io.Serializable;
import java.util.List;

public class Coleta implements Serializable {

	private static final long serialVersionUID = -3524332189543598469L;

	private String faixaDeValores;
	private List<Devedor> devedores;
	private Log log;

	public Coleta(String faixaDeValores, List<Devedor> devedores, Log log) {
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

	public Log obterLog() {
		return log;
	}

}
