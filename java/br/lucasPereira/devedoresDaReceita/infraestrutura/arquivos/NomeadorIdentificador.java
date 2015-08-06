package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

public class NomeadorIdentificador extends NomeadorAbstrato implements Nomeador {

	private static final String FORMATO_DO_NOME = "%s/%s.%s";

	private String identificador;

	public NomeadorIdentificador(Nomeador nomeador, String identificador) {
		super(nomeador);
		this.identificador = identificador;
	}

	@Override
	public final String obterNome() {
		return String.format(FORMATO_DO_NOME, obterDiretorio(), identificador, obterDiretorio());
	}

}
