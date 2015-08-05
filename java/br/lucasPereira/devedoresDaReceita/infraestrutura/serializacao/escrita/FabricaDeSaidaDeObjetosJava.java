package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FabricaDeSaidaDeObjetosJava implements FabricaDeSaidaDeObjetos {

	@Override
	public ObjectOutputStream construir(OutputStream saida) throws IOException {
		return new ObjectOutputStream(saida);
	}

}
