package br.lucasPereira.devedoresDaReceita.coletas.imoveis.executavel.visualizacao;

import br.lucasPereira.devedoresDaReceita.coletas.imoveis.ColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.LeitorDeColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;
import br.lucasPereira.devedoresDaReceita.modelo.Imovel;

public class VisualizadorDeColetaDeImoveis {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new VisualizadorDeColetaDeImoveis("ser/coleta_imoveis_2015-07-27_23:56.ser").visualizar();
	}

	public VisualizadorDeColetaDeImoveis(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void visualizar() {
		ColetaDeImoveis coleta = new LeitorDeColetaDeImoveis(nomeDoArquivo).carregar();
		for (Devedor devedor : coleta.obterDevedores()) {
			System.out.println(devedor.obterCpf());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValor());
			System.out.println();
		}
		for (Devedor devedor : coleta.obterDevedoresComImoveisColetados()) {
			System.out.println(devedor.obterCpf());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValor());
			for (Imovel imovel : devedor.obterImoveis()) {
				System.out.println(imovel.obterCodigo());
				System.out.println(imovel.obterNome());
				System.out.println(imovel.obterArea());
				System.out.println(imovel.obterCidade());
				System.out.println(imovel.obterEstado());
				System.out.println(imovel.obterDataDeEntrega());
				System.out.println(imovel.obterSituacaoDaDeclaracao());
			}
			System.out.println();
		}
		System.out.println(coleta.obterDevedores().size());
		System.out.println(coleta.obterDevedoresComImoveisColetados().size());
	}

}
