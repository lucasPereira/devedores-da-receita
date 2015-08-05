package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class EscritorDeCsv<T> extends EscritorDeArquivo<NomeadorDeArquivosCsv> {

	private static final String SEPARADOR_DE_COLUNA = ";";
	private static final String SEPARADOR_DE_LINHA = System.lineSeparator();
	private static final String CAMPO_VAZIO = "-";

	private List<String> campos;
	private StringBuilder linhas;

	public EscritorDeCsv() {
		this.linhas = new StringBuilder();
	}

	public final void salvar(List<T> elementos) {
		try {
			NomeadorDeArquivosCsv nomeadorDeArquivos = construirNomeador();
			nomeadorDeArquivos.criarDiretorio();
			String nomeDoArquivo = nomeadorDeArquivos.obterNomeComDataHorarioAtual();
			FileWriter escritor = new FileWriter(nomeDoArquivo);
			for (T elemento : elementos) {
				campos = new LinkedList<>();
				adicionarElemento(elemento);
			}
			escritor.write(linhas.toString());
			escritor.close();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	}

	public final void adicionarCampo(String campo) {
		campos.add(campo.replaceAll(SEPARADOR_DE_COLUNA, ""));
	}

	public final void adicionarCampo() {
		campos.add(CAMPO_VAZIO);
	}

	public final void finalizarLinha() {
		Iterator<String> iteradorDeCampos = campos.iterator();
		while (iteradorDeCampos.hasNext()) {
			linhas.append(iteradorDeCampos.next());
			if (iteradorDeCampos.hasNext()) {
				linhas.append(SEPARADOR_DE_COLUNA);
			}
		}
		linhas.append(SEPARADOR_DE_LINHA);
	}

	public abstract void adicionarElemento(T elemento);

}
