package br.lucasPereira.devedoresDaReceita;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EscritorDeColeta {

	private String nomeDoArquivo;

	public EscritorDeColeta(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void salvarColeta(Coleta coleta) {
		try {
			FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
			ObjectOutputStream saida = new ObjectOutputStream(arquivo);
			saida.writeObject(coleta);
			saida.close();
		} catch (FileNotFoundException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

}
