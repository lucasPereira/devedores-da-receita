package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

public abstract class EscritorDeArquivo<T extends NomeadorDeArquivos> {

	public abstract T construirNomeador();

}
