package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class EscritorDeCsv<T> extends EscritorDeArquivo<List<T>> {

	private static final String CAMPO_VAZIO = "-";
	private static final String SEPARADOR_DE_COLUNA = ";";
	private static final String SEPARADOR_DE_LINHA = System.lineSeparator();

	private StringBuilder linhas;

	public EscritorDeCsv(Nomeador nomeador) {
		super(nomeador);
		this.linhas = new StringBuilder();
	}

	@Override
	public final void salvar(List<T> elementos, String nomeDoArquivo) throws IOException {
		FileWriter escritor = new FileWriter(nomeDoArquivo);
		for (T elemento : elementos) {
			adicionarElemento(elemento);
		}
		escritor.write(linhas.toString());
		escritor.close();
	}

	public final void adicionarCampo(String campo) {
		linhas.append(obterCampoSeguro(campo));
		linhas.append(SEPARADOR_DE_COLUNA);
	}

	public final void adicionarCampo() {
		linhas.append(CAMPO_VAZIO);
		linhas.append(SEPARADOR_DE_COLUNA);
	}

	public final void finalizarLinha(String campo) {
		linhas.append(obterCampoSeguro(campo));
		linhas.append(SEPARADOR_DE_LINHA);
	}

	public final void finalizarLinha() {
		linhas.append(CAMPO_VAZIO);
		linhas.append(SEPARADOR_DE_LINHA);
	}

	private String obterCampoSeguro(String campo) {
		return campo.replaceAll(SEPARADOR_DE_COLUNA, "");
	}

	public abstract void adicionarElemento(T elemento);

}
