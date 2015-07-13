package br.lucasPereira.devedoresDaReceita;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Log implements Iterable<EntradaDeLog>, Serializable {

	private static final long serialVersionUID = 508387131534645735L;

	private List<EntradaDeLog> entradas;

	public Log() {
		entradas = new LinkedList<>();
	}

	public void adicionarEntrada(ContrutorDeEntradaDeLog construtorDeEntradaDeLog) {
		construtorDeEntradaDeLog.naPagina(entradas.size() + 1);
		entradas.add(construtorDeEntradaDeLog.construir());
	}

	@Override
	public Iterator<EntradaDeLog> iterator() {
		return entradas.iterator();
	}

}
