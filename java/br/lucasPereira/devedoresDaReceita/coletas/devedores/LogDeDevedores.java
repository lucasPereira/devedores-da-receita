package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LogDeDevedores implements Iterable<EntradaDeLogDeDevedores>, Serializable {

	private static final long serialVersionUID = 508387131534645735L;

	private List<EntradaDeLogDeDevedores> entradas;

	public LogDeDevedores() {
		entradas = new LinkedList<>();
	}

	public void adicionarEntrada(ContrutorDeEntradaDeLogDeDevedores construtorDeEntradaDeLog) {
		construtorDeEntradaDeLog.naPagina(entradas.size() + 1);
		entradas.add(construtorDeEntradaDeLog.construir());
	}

	@Override
	public Iterator<EntradaDeLogDeDevedores> iterator() {
		return entradas.iterator();
	}

}
