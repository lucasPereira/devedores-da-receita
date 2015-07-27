package br.lucasPereira.devedoresDaReceita.coletas.devedores.executavel.visualizacao;

import br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.EntradaDeLogDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.LeitorDeColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class VisualizadorDeColetaDeDevedores {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new VisualizadorDeColetaDeDevedores("ser/coleta_devedores_2015-07-27_02:56.ser").visualizar();
	}

	public VisualizadorDeColetaDeDevedores(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void visualizar() {
		ColetaDeDevedores coleta = new LeitorDeColetaDeDevedores(nomeDoArquivo).carregarColeta();
		System.out.println(coleta.obterFaixaDeValores());
		System.out.println();
		for (Devedor devedor : coleta.obterDevedores()) {
			System.out.println(devedor.obterCpf());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValor());
			System.out.println();
		}
		for (EntradaDeLogDeDevedores entradaDeLog : coleta.obterLog()) {
			System.out.println(entradaDeLog.obterPagina());
			System.out.println(entradaDeLog.obterQuantidadeDeDevedores());
			System.out.println(entradaDeLog.obterQuantidadesDeColunas());
			System.out.println();
		}
		System.out.println(coleta.obterDevedores().size());
	}

}
