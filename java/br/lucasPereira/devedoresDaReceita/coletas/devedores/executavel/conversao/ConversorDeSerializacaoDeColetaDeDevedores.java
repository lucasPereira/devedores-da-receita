package br.lucasPereira.devedoresDaReceita.coletas.devedores.executavel.conversao;

import br.lucasPereira.devedoresDaReceita.coletas.devedores.ColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.EscritorDeColetaDeDevedores;
import br.lucasPereira.devedoresDaReceita.coletas.devedores.LeitorDeColetaDeDevedores;

public class ConversorDeSerializacaoDeColetaDeDevedores {

	private String nomeDoArquivo;
	private String nomeDoNovoArquivo;

	public static void main(String[] args) {
		new ConversorDeSerializacaoDeColetaDeDevedores("ser/coleta_2015-07-27_02:56.ser", "ser/coleta_devedores_2015-07-27_02:56.ser").converter();
	}

	public ConversorDeSerializacaoDeColetaDeDevedores(String nomeDoArquivo, String nomeDoNovoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
		this.nomeDoNovoArquivo = nomeDoNovoArquivo;
	}

	private void converter() {
		FabricaDeLeitorDeObjetosParaColetaDeDevedores fabricaDeLeitorDeObjetos = new FabricaDeLeitorDeObjetosParaColetaDeDevedores();
		LeitorDeColetaDeDevedores leitor = new LeitorDeColetaDeDevedores(nomeDoArquivo, fabricaDeLeitorDeObjetos);
		EscritorDeColetaDeDevedores escritor = new EscritorDeColetaDeDevedores(nomeDoNovoArquivo);
		ColetaDeDevedores coletaDeDevedores = leitor.carregar();
		escritor.salvar(coletaDeDevedores);
	}

}
