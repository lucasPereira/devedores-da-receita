package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class EscritorDeCsv<T> extends EscritorDeArquivo<List<T>> {

	private static final String CAMPO_VAZIO = "-";
	private static final String SEPARADOR_DE_COLUNA = ";";
	private static final String SEPARADOR_DE_LINHA = System.lineSeparator();

	private List<String> campos;
	private StringBuilder linhas;

	public EscritorDeCsv(Nomeador nomeador) {
		super(nomeador);
		this.linhas = new StringBuilder();
	}

	@Override
	public final void salvar(List<T> elementos, String nomeDoArquivo) throws IOException {
		FileWriter escritor = new FileWriter(nomeDoArquivo);
		for (T elemento : elementos) {
			campos = new LinkedList<>();
			adicionarElemento(elemento);
		}
		escritor.write(linhas.toString());
		escritor.close();
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
