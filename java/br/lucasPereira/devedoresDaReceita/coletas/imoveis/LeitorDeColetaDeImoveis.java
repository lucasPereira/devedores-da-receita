package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.LeitorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura.FabricaDeEntradaDeObjetosJava;

public final class LeitorDeColetaDeImoveis extends LeitorDeSer<ColetaDeImoveis> {

	public LeitorDeColetaDeImoveis(String nomeDoArquivo) {
		super(nomeDoArquivo, new FabricaDeEntradaDeObjetosJava());
	}

}
