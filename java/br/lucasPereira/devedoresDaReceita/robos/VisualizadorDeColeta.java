package br.lucasPereira.devedoresDaReceita.robos;

import br.lucasPereira.devedoresDaReceita.Coleta;
import br.lucasPereira.devedoresDaReceita.Devedor;
import br.lucasPereira.devedoresDaReceita.EntradaDeLog;
import br.lucasPereira.devedoresDaReceita.LeitorDeColeta;

public class VisualizadorDeColeta {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new VisualizadorDeColeta("ser/coleta_2015-07-27_02:56.ser").visualizar();
	}

	public VisualizadorDeColeta(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void visualizar() {
		Coleta coleta = new LeitorDeColeta(nomeDoArquivo).carregarColeta();
		System.out.println(coleta.obterFaixaDeValores());
		System.out.println();
		for (Devedor devedor : coleta.obterDevedores()) {
			System.out.println(devedor.obterCpf());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValor());
			System.out.println();
		}
		for (EntradaDeLog entradaDeLog : coleta.obterLog()) {
			System.out.println(entradaDeLog.obterPagina());
			System.out.println(entradaDeLog.obterQuantidadeDeDevedores());
			System.out.println(entradaDeLog.obterQuantidadesDeColunas());
			System.out.println();
		}
	}

}
