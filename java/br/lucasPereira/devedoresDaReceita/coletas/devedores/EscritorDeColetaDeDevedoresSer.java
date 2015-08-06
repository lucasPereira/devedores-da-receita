package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.Nomeador;

public final class EscritorDeColetaDeDevedoresSer extends EscritorDeSer<ColetaDeDevedores> {

	public EscritorDeColetaDeDevedoresSer(Nomeador nomeador) {
		super(nomeador);
	}

}
