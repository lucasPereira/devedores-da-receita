package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.Nomeador;

public final class EscritorDeColetaDeImoveisSer extends EscritorDeSer<ColetaDeImoveis> {

	public EscritorDeColetaDeImoveisSer(Nomeador nomeador) {
		super(nomeador);
	}

}
