package br.lucasPereira.devedoresDaReceita.coletas.imoveis.execucao;

import br.lucasPereira.devedoresDaReceita.coletas.imoveis.ColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.LeitorDeColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;
import br.lucasPereira.devedoresDaReceita.modelo.Imovel;

public class VisualizadorDeColetaDeImoveis {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new VisualizadorDeColetaDeImoveis("ser/imoveis_2015.08.05_16.44.ser").visualizar();
	}

	public VisualizadorDeColetaDeImoveis(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void visualizar() {
		ColetaDeImoveis coleta = new LeitorDeColetaDeImoveis(nomeDoArquivo).carregar();
		for (Devedor devedor : coleta.obterDevedores()) {
			System.out.println(devedor.obterIdentificador());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValorDaDivida());
			System.out.println();
		}
		for (Devedor devedor : coleta.obterDevedoresComImoveisColetados()) {
			System.out.println(devedor.obterIdentificador());
			System.out.println(devedor.obterNome());
			System.out.println(devedor.obterValorDaDivida());
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
