package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.LeitorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura.FabricaDeEntradaDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura.FabricaDeEntradaDeObjetosJava;

public class LeitorDeColetaDeDevedores extends LeitorDeSer<ColetaDeDevedores> {

	public LeitorDeColetaDeDevedores(String nomeDoArquivo) {
		this(nomeDoArquivo, new FabricaDeEntradaDeObjetosJava());
	}

	public LeitorDeColetaDeDevedores(String nomeDoArquivo, FabricaDeEntradaDeObjetos fabricaDeLeitorDeObjetos) {
		super(nomeDoArquivo, fabricaDeLeitorDeObjetos);
	}

}
