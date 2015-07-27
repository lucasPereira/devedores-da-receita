package br.lucasPereira.devedoresDaReceita.coletas.devedores.executavel.conversao;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import br.lucasPereira.devedoresDaReceita.infraestrutura.FabricaDeLeitorDeObjetos;

public class FabricaDeLeitorDeObjetosParaColetaDeDevedores implements FabricaDeLeitorDeObjetos {

	@Override
	public ObjectInputStream construir(InputStream entrada) throws IOException {
		return new LeitorDeObjetoParaColetaDeDevedores(entrada);
	}

}
