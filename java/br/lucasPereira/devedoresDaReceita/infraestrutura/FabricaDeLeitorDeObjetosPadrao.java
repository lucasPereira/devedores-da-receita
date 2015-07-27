package br.lucasPereira.devedoresDaReceita.infraestrutura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class FabricaDeLeitorDeObjetosPadrao implements FabricaDeLeitorDeObjetos {

	@Override
	public ObjectInputStream construir(InputStream entrada) throws IOException {
		return new ObjectInputStream(entrada);
	}

}
