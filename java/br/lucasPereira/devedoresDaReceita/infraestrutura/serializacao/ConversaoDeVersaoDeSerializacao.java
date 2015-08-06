package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao;

import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.EscritorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.LeitorDeSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorDataHorario;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorConcreto;
import br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos.NomeadorSer;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita.FabricaDeSaidaDeObjetos;
import br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita.FabricaDeSaidaDeObjetosRenomeados;

public abstract class ConversaoDeVersaoDeSerializacao<Antigo, Novo> {

	public abstract Novo converterParaNovaVersao(Antigo elemento);

	public abstract String obterNomeDoArquivoDaVersaoAntiga();

	public abstract String obterPrefixoDoArquivoDaVersaoNova();

	public abstract Class<?> obterClasseAntigaTemporaria();

	public abstract Class<?> obterClasseNovaTemporaria();

	private final LeitorDeSer<Antigo> obterLeitorDaVersaoAntiga() {
		return new LeitorDeSer<Antigo>(obterNomeDoArquivoDaVersaoAntiga());
	}

	private FabricaDeSaidaDeObjetos obterFabricaDeSaidaDeObjetos() {
		Class<?> classeAntiga = obterClasseAntigaTemporaria();
		Class<?> classeNova = obterClasseNovaTemporaria();
		String nomeAntiga = classeAntiga.getName();
		String nomeNova = classeNova.getName();
		return new FabricaDeSaidaDeObjetosRenomeados(nomeNova, nomeAntiga);
	}

	private final EscritorDeSer<Novo> obterEscritorDaVersaoNova() {
		String prefixo = obterPrefixoDoArquivoDaVersaoNova();
		NomeadorSer nomeador = new NomeadorSer(new NomeadorDataHorario(new NomeadorConcreto(), prefixo));
		return new EscritorDeSer<Novo>(nomeador, obterFabricaDeSaidaDeObjetos());
	}

	public final void converter() {
		Antigo antigo = obterLeitorDaVersaoAntiga().carregar();
		Novo novo = converterParaNovaVersao(antigo);
		obterEscritorDaVersaoNova().salvar(novo);
	}

}
