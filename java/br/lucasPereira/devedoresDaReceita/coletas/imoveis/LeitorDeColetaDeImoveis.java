package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import br.lucasPereira.devedoresDaReceita.infraestrutura.FabricaDeLeitorDeObjetosPadrao;
import br.lucasPereira.devedoresDaReceita.infraestrutura.Leitor;

public class LeitorDeColetaDeImoveis extends Leitor<ColetaDeImoveis> {

	public LeitorDeColetaDeImoveis(String nomeDoArquivo) {
		super(nomeDoArquivo, new FabricaDeLeitorDeObjetosPadrao());
	}

}
