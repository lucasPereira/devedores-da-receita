package br.lucasPereira.devedoresDaReceita.coletas.devedores.executavel.conversao;

import java.io.IOException;
import java.io.InputStream;

import br.lucasPereira.devedoresDaReceita.infraestrutura.LeitorDeObjetosComRenomeacaoDePacote;

public class LeitorDeObjetoParaColetaDeDevedores extends LeitorDeObjetosComRenomeacaoDePacote {

	protected LeitorDeObjetoParaColetaDeDevedores(InputStream entrada) throws IOException, SecurityException {
		super(entrada);
		renomear("br.lucasPereira.devedoresDaReceita.Coleta", "br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores");
		renomear("br.lucasPereira.devedoresDaReceita.ConstrutorDeEntradaDeLog", "br.lucasPereira.devedoresDaReceita.coletas.devedores.ConstrutorDeEntradaDeLogDeDevedores");
		renomear("br.lucasPereira.devedoresDaReceita.EntradaDeLog", "br.lucasPereira.devedoresDaReceita.coletas.devedores.EntradaDeLogDeDevedores");
		renomear("br.lucasPereira.devedoresDaReceita.Log", "br.lucasPereira.devedoresDaReceita.coletas.devedores.LogDeDevedores");
		renomear("br.lucasPereira.devedoresDaReceita.Devedor", "br.lucasPereira.devedoresDaReceita.modelo.Devedor");
	}

}
