package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public interface FabricaDeSaidaDeObjetos {

	public ObjectOutputStream construir(OutputStream saida) throws IOException;

}
