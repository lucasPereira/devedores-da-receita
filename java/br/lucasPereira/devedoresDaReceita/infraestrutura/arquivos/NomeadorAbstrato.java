package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

public class NomeadorAbstrato implements Nomeador {

	private Nomeador nomeador;

	public NomeadorAbstrato(Nomeador nomeador) {
		this.nomeador = nomeador;
	}

	@Override
	public String obterNome() {
		return nomeador.obterNome();
	}

	@Override
	public String obterDiretorio() {
		return nomeador.obterDiretorio();
	}

}
