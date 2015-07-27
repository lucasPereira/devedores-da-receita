package br.lucasPereira.devedoresDaReceita.infraestrutura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public interface FabricaDeLeitorDeObjetos {

	public ObjectInputStream construir(InputStream entrada) throws IOException;

}
