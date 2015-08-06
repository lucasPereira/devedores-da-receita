package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

public final class NomeadorSer extends NomeadorAbstrato implements Nomeador {

	public NomeadorSer(Nomeador nomeador) {
		super(nomeador);
	}

	@Override
	public String obterDiretorio() {
		return "ser";
	}

}
