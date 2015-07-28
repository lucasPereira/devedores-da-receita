package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import br.lucasPereira.devedoresDaReceita.infraestrutura.Escritor;

public class EscritorDeColetaDeDevedores extends Escritor<ColetaDeDevedores> {

	public EscritorDeColetaDeDevedores(String nomeDoArquivo) {
		super(nomeDoArquivo);
	}

}
