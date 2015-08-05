package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura.FabricaDeEntradaDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura.FabricaDeEntradaDeObjetosJava;

public class LeitorDeSer<T> {

	private String nomeDoArquivo;
	private FabricaDeEntradaDeObjetos fabricaDeEntradaDeObjetos;

	public LeitorDeSer(String nomeDoArquivo, FabricaDeEntradaDeObjetos fabricaDeEntradaDeObjetos) {
		this.nomeDoArquivo = nomeDoArquivo;
		this.fabricaDeEntradaDeObjetos = fabricaDeEntradaDeObjetos;
	}

	public LeitorDeSer(String nomeDoArquivo) {
		this(nomeDoArquivo, new FabricaDeEntradaDeObjetosJava());
	}

	public T carregar() {
		try {
			FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
			ObjectInputStream entrada = fabricaDeEntradaDeObjetos.construir(arquivo);
			@SuppressWarnings("unchecked") T objeto = (T) entrada.readObject();
			entrada.close();
			return objeto;
		} catch (FileNotFoundException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		} catch (ClassNotFoundException excecao) {
			excecao.printStackTrace();
		}
		return null;
	}

}
