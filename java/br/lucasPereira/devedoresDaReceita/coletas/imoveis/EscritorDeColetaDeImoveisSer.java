package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorDeArquivosSer;

public final class EscritorDeColetaDeImoveisSer extends EscritorDeSer<ColetaDeImoveis> {

	@Override
	public NomeadorDeArquivosSer construirNomeador() {
		return new NomeadorDeArquivosSer("imoveis");
	}

}
