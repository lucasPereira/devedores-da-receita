package br.lucasPereira.devedoresDaReceita.coletas.imoveis.execucao;

import br.lucasPereira.devedoresDaReceita.coletas.imoveis.ColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.LeitorDeColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;
import br.lucasPereira.devedoresDaReceita.modelo.Imovel;

public class VisualizadorDeColetaDeImoveis {

	private String nomeDoArquivo;

	public static void main(String[] args) {
		new VisualizadorDeColetaDeImoveis("ser/imoveis.ser").visualizar();
	}

	public VisualizadorDeColetaDeImoveis(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public void visualizar() {
		ColetaDeImoveis coleta = new LeitorDeColetaDeImoveis(nomeDoArquivo).carregar();
		System.out.println(coleta.obterNomeDaColetaDeDevedores());
		System.out.println(coleta.obterPrimeiroIndiceDaColetaDeDevedores());
		System.out.println(coleta.obterUltimoIndiceDaColetaDeDevedores());
		System.out.println();
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
		System.out.println(coleta.obterDevedoresComImoveisColetados().size());
	}

}
