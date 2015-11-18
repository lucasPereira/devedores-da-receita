package br.lucasPereira.devedoresDaReceita.coletas.devedores.execucao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.EscritorDeColetaDeDevedoresCsv;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.EscritorDeColetaDeDevedoresSer;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.LeitorDeColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.Nomeador;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorConcreto;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorCsv;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorIdentificador;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorSer;
import br.lucasPereira.devedoresDaReceita.modelo.Devedor;

public class CompactadorColetaDeDevedores {

	public static void main(String[] argumentos) {
		Set<Devedor> devedores = new HashSet<>();
		ColetaDeDevedores coleta1 = new LeitorDeColetaDeDevedores("ser/devedores_2015.11.03_08.49.ser").carregar();
		ColetaDeDevedores coleta2 = new LeitorDeColetaDeDevedores("ser/devedores_2015.11.03_12.11.ser").carregar();
		ColetaDeDevedores coleta3 = new LeitorDeColetaDeDevedores("ser/devedores_2015.11.03_17.37.ser").carregar();
		ColetaDeDevedores coleta4 = new LeitorDeColetaDeDevedores("ser/devedores_2015.11.04_20.59.ser").carregar();
		devedores.addAll(coleta1.obterDevedores());
		devedores.addAll(coleta2.obterDevedores());
		devedores.addAll(coleta3.obterDevedores());
		devedores.addAll(coleta4.obterDevedores());
		System.out.println(coleta1.obterDevedores().size() + coleta2.obterDevedores().size() + coleta3.obterDevedores().size() + coleta4.obterDevedores().size());
		System.out.println(devedores.size());
		List<Devedor> devedoresOrdenados = new ArrayList<>(devedores);
		Collections.sort(devedoresOrdenados);
		Collections.reverse(devedoresOrdenados);
		Nomeador nomeadorCsv = new NomeadorIdentificador(new NomeadorCsv(new NomeadorConcreto()), "devedores_entre_1_milhao_e_10_milhoes");
		Nomeador nomeadorSer = new NomeadorIdentificador(new NomeadorSer(new NomeadorConcreto()), "devedores_entre_1_milhao_e_10_milhoes");
		new EscritorDeColetaDeDevedoresCsv(nomeadorCsv).salvar(devedoresOrdenados);
		new EscritorDeColetaDeDevedoresSer(nomeadorSer).salvar(new ColetaDeDevedores(coleta1.obterFaixaDeValores(), devedoresOrdenados));
	}

}
