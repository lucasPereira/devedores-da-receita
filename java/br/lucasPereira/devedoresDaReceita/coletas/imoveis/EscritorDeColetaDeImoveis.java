package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import br.lucasPereira.devedoresDaReceita.infraestrutura.Escritor;

public class EscritorDeColetaDeImoveis extends Escritor<ColetaDeImoveis> {

	public EscritorDeColetaDeImoveis(String nomeDoArquivo) {
		super(nomeDoArquivo);
	}

}
