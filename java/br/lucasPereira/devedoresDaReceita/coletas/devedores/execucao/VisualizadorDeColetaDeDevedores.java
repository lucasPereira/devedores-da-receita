package br.lucasPereira.devedoresDaReceita.coletas.devedores.execucao;

import br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.LeitorDeColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class VisualizadorDeColetaDeDevedores {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new VisualizadorDeColetaDeDevedores("ser/devedores_entre_1_milhao_e_10_milhoes.ser").visualizar();
	}

	public VisualizadorDeColetaDeDevedores(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void visualizar() {
		ColetaDeDevedores coleta = new LeitorDeColetaDeDevedores(nomeDoArquivo).carregar();
		for (Devedor devedor : coleta.obterDevedores()) {
			System.out.println(devedor.obterIdentificador());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValorDaDivida());
			System.out.println();
		}
		System.out.println(coleta.obterFaixaDeValores());
		System.out.println(coleta.obterDevedores().size());
	}

}
