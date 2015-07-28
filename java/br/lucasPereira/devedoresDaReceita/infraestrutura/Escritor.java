package br.lucasPereira.devedoresDaReceita.infraestrutura;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Escritor<T> {

	private String nomeDoArquivo;

	public Escritor(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void salvar(T objeto) {
		try {
			FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
			ObjectOutputStream saida = new ObjectOutputStream(arquivo);
			saida.writeObject(objeto);
			saida.close();
		} catch (FileNotFoundException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

}
