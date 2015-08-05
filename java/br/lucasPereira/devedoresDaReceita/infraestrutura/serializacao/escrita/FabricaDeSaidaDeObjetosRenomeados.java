package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FabricaDeSaidaDeObjetosRenomeados implements FabricaDeSaidaDeObjetos {

	private String nomeAntigo;
	private String nomeNovo;

	public FabricaDeSaidaDeObjetosRenomeados(String nomeAntigo, String nomeNovo) {
		this.nomeAntigo = nomeAntigo;
		this.nomeNovo = nomeNovo;
	}

	@Override
	public ObjectOutputStream construir(OutputStream saida) throws IOException {
		SaidaDeObjetos saidaDeObjetos = new SaidaDeObjetos(saida);
		saidaDeObjetos.renomear(nomeAntigo, nomeNovo);
		return saidaDeObjetos;
	}

}
