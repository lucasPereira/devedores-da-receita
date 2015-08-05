package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.escrita;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class SaidaDeObjetos extends ObjectOutputStream {

	private Map<String, String> mapaDeMudancasDeNome;

	protected SaidaDeObjetos(OutputStream saida) throws IOException, SecurityException {
		super(saida);
		mapaDeMudancasDeNome = new HashMap<>();
	}

	@Override
	protected void writeClassDescriptor(ObjectStreamClass descricaoDaClasse) throws IOException {
		String nomeAntigo = descricaoDaClasse.getName();
		if (mapaDeMudancasDeNome.containsKey(nomeAntigo)) {
			String nomeNovo = mapaDeMudancasDeNome.get(nomeAntigo);
			super.writeClassDescriptor(construirDescricaoNova(descricaoDaClasse, nomeNovo));
		}
		super.writeClassDescriptor(descricaoDaClasse);
	}

	private ObjectStreamClass construirDescricaoNova(ObjectStreamClass descricaoDaClasse, String nomeNovo) {
		try {
			descricaoDaClasse = ObjectStreamClass.lookup(Class.forName(nomeNovo));
		} catch (ClassNotFoundException excecao) {
			excecao.printStackTrace();
		}
		return descricaoDaClasse;
	}

	protected void renomear(String antigo, String novo) {
		mapaDeMudancasDeNome.put(antigo, novo);
	}

}
