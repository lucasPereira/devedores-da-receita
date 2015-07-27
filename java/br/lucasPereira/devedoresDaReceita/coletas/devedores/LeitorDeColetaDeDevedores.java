package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import br.lucasPereira.devedoresDaReceita.infraestrutura.FabricaDeLeitorDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.FabricaDeLeitorDeObjetosPadrao;

public class LeitorDeColetaDeDevedores {

	private String nomeDoArquivo;
	private FabricaDeLeitorDeObjetos fabricaDeLeitorDeObjetos;

	public LeitorDeColetaDeDevedores(String nomeDoArquivo) {
		this(new FabricaDeLeitorDeObjetosPadrao(), nomeDoArquivo);
	}

	public LeitorDeColetaDeDevedores(FabricaDeLeitorDeObjetos fabricaDeLeitorDeObjetos, String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
		this.fabricaDeLeitorDeObjetos = fabricaDeLeitorDeObjetos;
	}

	public ColetaDeDevedores carregarColeta() {
		try {
			FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
			ObjectInputStream entrada = fabricaDeLeitorDeObjetos.construir(arquivo);
			ColetaDeDevedores coleta = (ColetaDeDevedores) entrada.readObject();
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
