package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita.FabricaDeSaidaDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita.FabricaDeSaidaDeObjetosJava;

public abstract class EscritorDeSer<T> extends EscritorDeArquivo<NomeadorDeArquivosSer> {

	private FabricaDeSaidaDeObjetos fabricaDeSaidaDeObjetos;

	public EscritorDeSer(FabricaDeSaidaDeObjetos fabricaDeSaidaDeObjetos) {
		this.fabricaDeSaidaDeObjetos = fabricaDeSaidaDeObjetos;
	}

	public EscritorDeSer() {
		this(new FabricaDeSaidaDeObjetosJava());
	}

	public final void salvar(T objeto) {
		try {
			NomeadorDeArquivosSer nomeadorDeArquivos = construirNomeador();
			nomeadorDeArquivos.criarDiretorio();
			String nomeDoArquivo = nomeadorDeArquivos.obterNomeComDataHorarioAtual();
			FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
			ObjectOutputStream saida = fabricaDeSaidaDeObjetos.construir(arquivo);
			saida.writeObject(objeto);
			saida.close();
		} catch (FileNotFoundException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

}
