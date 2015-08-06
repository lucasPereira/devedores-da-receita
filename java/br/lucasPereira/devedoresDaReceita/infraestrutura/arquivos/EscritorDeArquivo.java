package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.File;
import java.io.IOException;

public abstract class EscritorDeArquivo<U> {

	private Nomeador nomeador;

	public EscritorDeArquivo(Nomeador nomeador) {
		this.nomeador = nomeador;
	}

	public final void salvar(U elemento) {
		new File(nomeador.obterDiretorio()).mkdirs();
		try {
			salvar(elemento, nomeador.obterNome());
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

	public abstract void salvar(U elemento, String obterNome) throws IOException;

}
