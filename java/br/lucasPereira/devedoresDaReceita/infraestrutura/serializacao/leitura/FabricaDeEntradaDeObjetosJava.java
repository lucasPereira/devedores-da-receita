package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class FabricaDeEntradaDeObjetosJava implements FabricaDeEntradaDeObjetos {

	@Override
	public ObjectInputStream construir(InputStream entrada) throws IOException {
		return new ObjectInputStream(entrada);
	}

}
