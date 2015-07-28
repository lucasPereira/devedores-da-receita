package br.lucasPereira.devedoresDaReceita.infraestrutura;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Leitor<T> {

	private String nomeDoArquivo;
	private FabricaDeLeitorDeObjetos fabricaDeLeitorDeObjetos;

	public Leitor(String nomeDoArquivo, FabricaDeLeitorDeObjetos fabricaDeLeitorDeObjetos) {
		this.nomeDoArquivo = nomeDoArquivo;
		this.fabricaDeLeitorDeObjetos = fabricaDeLeitorDeObjetos;
	}

	public T carregar() {
		try {
			FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
			ObjectInputStream entrada = fabricaDeLeitorDeObjetos.construir(arquivo);
			@SuppressWarnings("unchecked")
			T objeto = (T) entrada.readObject();
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
