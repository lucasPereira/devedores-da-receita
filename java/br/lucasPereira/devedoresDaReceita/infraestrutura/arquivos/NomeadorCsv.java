package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

public final class NomeadorCsv extends NomeadorAbstrato implements Nomeador {

	public NomeadorCsv(Nomeador nomeador) {
		super(nomeador);
	}

	@Override
	public String obterDiretorio() {
		return "csv";
	}

}
