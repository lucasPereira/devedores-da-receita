package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class FabricaDeEntradaDeObjetosRenomeados implements FabricaDeEntradaDeObjetos {

	private String nomeAntigo;
	private String nomeNovo;

	public FabricaDeEntradaDeObjetosRenomeados(String nomeAntigo, String nomeNovo) {
		this.nomeAntigo = nomeAntigo;
		this.nomeNovo = nomeNovo;
	}

	@Override
	public ObjectInputStream construir(InputStream entrada) throws IOException {
		EntradaDeObjetos entradaDeObjetos = new EntradaDeObjetos(entrada);
		entradaDeObjetos.renomear(nomeAntigo, nomeNovo);
		return entradaDeObjetos;
	}

}
