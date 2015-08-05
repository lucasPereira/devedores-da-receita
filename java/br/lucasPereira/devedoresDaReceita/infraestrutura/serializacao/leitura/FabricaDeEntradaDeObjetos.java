package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public interface FabricaDeEntradaDeObjetos {

	public ObjectInputStream construir(InputStream entrada) throws IOException;

}
