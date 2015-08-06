package br.lucasPereira.devedoresDaReceita.coletas.devedores;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeCsv;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.Nomeador;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public final class EscritorDeColetaDeDevedoresCsv extends EscritorDeCsv<Devedor> {

	public EscritorDeColetaDeDevedoresCsv(Nomeador nomeador) {
		super(nomeador);
	}

	@Override
	public void adicionarElemento(Devedor elemento) {
		adicionarCampo(elemento.obterIdentificador());
		adicionarCampo(elemento.obterIdentificadorApenasComNumeros());
		adicionarCampo(elemento.obterNome());
		adicionarCampo(elemento.obterValorDaDivida());
		finalizarLinha();
	}

}
