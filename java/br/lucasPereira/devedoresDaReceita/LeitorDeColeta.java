package br.lucasPereira.devedoresDaReceita;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeitorDeColeta {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new LeitorDeColeta("ser/coleta_2015-07-13_10:23.ser").ler();
	}

	public LeitorDeColeta(String arquivo) {
		this.nomeDoArquivo = arquivo;
	}

	private void ler() {
		Coleta coleta = carregarColeta();
		System.out.println(coleta.obterFaixaDeValores());
		System.out.println();
		for (Devedor devedor : coleta.obterDevedores()) {
			System.out.println(devedor.obterCpf());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValor());
			System.out.println();
		}
		for (EntradaDeLog entradaDeLog : coleta.obterLog()) {
			System.out.println(entradaDeLog.obterPagina());
			System.out.println(entradaDeLog.obterQuantidadeDeDevedores());
			System.out.println(entradaDeLog.obterQuantidadesDeColunas());
			System.out.println();
		}
	}

	private Coleta carregarColeta() {
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
