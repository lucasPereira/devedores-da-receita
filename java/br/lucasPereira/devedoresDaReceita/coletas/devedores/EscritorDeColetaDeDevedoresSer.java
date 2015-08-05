package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorDeArquivosSer;

public class EscritorDeColetaDeDevedoresSer extends EscritorDeSer<ColetaDeDevedores> {

	@Override
	public NomeadorDeArquivosSer construirNomeador() {
		return new NomeadorDeArquivosSer("devedores");
	}

}
