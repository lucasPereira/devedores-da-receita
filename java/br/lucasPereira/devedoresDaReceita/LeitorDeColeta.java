package br.lucasPereira.devedoresDaReceita;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeitorDeColeta {

	private String nomeDoArquivo;

	public LeitorDeColeta(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public Coleta carregarColeta() {
		try {
			FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
			ObjectInputStream entrada = new ObjectInputStream(arquivo);
			Coleta coleta = (Coleta) entrada.readObject();
			entrada.close();
			return coleta;
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
