package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita.FabricaDeSaidaDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita.FabricaDeSaidaDeObjetosJava;

public class EscritorDeSer<T> extends EscritorDeArquivo<T> {

	private FabricaDeSaidaDeObjetos fabricaDeSaidaDeObjetos;

	public EscritorDeSer(Nomeador nomeador, FabricaDeSaidaDeObjetos fabricaDeSaidaDeObjetos) {
		super(nomeador);
		this.fabricaDeSaidaDeObjetos = fabricaDeSaidaDeObjetos;
	}

	public EscritorDeSer(Nomeador nomeador) {
		this(nomeador, new FabricaDeSaidaDeObjetosJava());
	}

	@Override
	public final void salvar(T elemento, String nomeDoArquivo) throws IOException {
		FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
		ObjectOutputStream saida = fabricaDeSaidaDeObjetos.construir(arquivo);
		saida.writeObject(elemento);
		saida.close();
	}

}
