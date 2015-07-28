package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import br.lucasPereira.devedoresDaReceita.infraestrutura.FabricaDeLeitorDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.FabricaDeLeitorDeObjetosPadrao;
import br.lucasPereira.devedoresDaReceita.infraestrutura.Leitor;

public class LeitorDeColetaDeDevedores extends Leitor<ColetaDeDevedores> {

	public LeitorDeColetaDeDevedores(String nomeDoArquivo) {
		this(nomeDoArquivo, new FabricaDeLeitorDeObjetosPadrao());
	}

	public LeitorDeColetaDeDevedores(String nomeDoArquivo, FabricaDeLeitorDeObjetos fabricaDeLeitorDeObjetos) {
		super(nomeDoArquivo, fabricaDeLeitorDeObjetos);
	}

}
