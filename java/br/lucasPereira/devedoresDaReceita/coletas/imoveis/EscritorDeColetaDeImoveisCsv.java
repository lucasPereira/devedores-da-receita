package br.lucasPereira.devedoresDaReceita.coletas.imoveis;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeCsv;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorDeArquivosCsv;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;
import br.lucasPereira.devedoresDaReceita.modelo.Imovel;

public final class EscritorDeColetaDeImoveisCsv extends EscritorDeCsv<Devedor> {

	@Override
	public NomeadorDeArquivosCsv construirNomeador() {
		return new NomeadorDeArquivosCsv("imoveis");
	}

	@Override
	public void adicionarElemento(Devedor elemento) {
		if (elemento.obterImoveis().isEmpty()) {
			adicionarLinhaApenasComDevedor(elemento);
		} else {
			adicionarLinhasComImoveis(elemento);
		}
	}

	private void adicionarCamposDoDevedor(Devedor elemento) {
		adicionarCampo(elemento.obterIdentificador());
		adicionarCampo(elemento.obterIdentificadorApenasComNumeros());
		adicionarCampo(elemento.obterNome());
		adicionarCampo(elemento.obterValorDaDivida());
		adicionarCampo(elemento.obterValorDaDividaApenasComNumeros());
	}

	private void adicionarLinhaApenasComDevedor(Devedor elemento) {
		adicionarCamposDoDevedor(elemento);
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		adicionarCampo();
		finalizarLinha();
	}

	private void adicionarLinhasComImoveis(Devedor elemento) {
		for (Imovel imovel : elemento.obterImoveis()) {
			adicionarCamposDoDevedor(elemento);
			adicionarCampo(imovel.obterCodigo());
			adicionarCampo(imovel.obterCodigoApenasComNumeros());
			adicionarCampo(imovel.obterNome());
			adicionarCampo(imovel.obterArea());
			adicionarCampo(imovel.obterAreaApenasComNumeros());
			adicionarCampo(imovel.obterCidade());
			adicionarCampo(imovel.obterEstado());
			adicionarCampo(imovel.obterDataDeEntrega());
			adicionarCampo(imovel.obterSituacaoDaDeclaracao());
			finalizarLinha();
		}
	}

}
