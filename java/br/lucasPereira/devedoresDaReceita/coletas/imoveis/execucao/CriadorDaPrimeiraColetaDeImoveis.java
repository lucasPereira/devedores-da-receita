package br.lucasPereira.devedoresDaReceita.coletas.imoveis.execucao;

import br.lucasPereira.devedoresDaReceita.coletas.imoveis.ColetaDeImoveis;
import br.lucasPereira.devedoresDaReceita.coletas.imoveis.EscritorDeColetaDeImoveisSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.Nomeador;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorConcreto;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorIdentificador;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorSer;

public class CriadorDaPrimeiraColetaDeImoveis {

	public static void main(String[] args) {
		new CriadorDaPrimeiraColetaDeImoveis().criar();
	}

	public void criar() {
		ColetaDeImoveis coleta = new ColetaDeImoveis(null, 0, 0, null);
		Nomeador nomeador = new NomeadorIdentificador(new NomeadorSer(new NomeadorConcreto()), "imoveis");
		 new EscritorDeColetaDeImoveisSer(nomeador).salvar(coleta);
	}

}
