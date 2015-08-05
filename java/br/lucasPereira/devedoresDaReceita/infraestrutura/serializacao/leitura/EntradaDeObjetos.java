package br.lucasPereira.devedoresDaReceita.infraestrutura.serializacao.leitura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashMap;
import java.util.Map;

public class EntradaDeObjetos extends ObjectInputStream {

	private Map<String, String> mapaDeMudancasDeNome;

	protected EntradaDeObjetos(InputStream entrada) throws IOException, SecurityException {
		super(entrada);
		mapaDeMudancasDeNome = new HashMap<>();
	}

	@Override
	protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
		ObjectStreamClass descricaoDaClasse = super.readClassDescriptor();
		String nomeAntigo = descricaoDaClasse.getName();
		if (mapaDeMudancasDeNome.containsKey(nomeAntigo)) {
			String nomeNovo = mapaDeMudancasDeNome.get(nomeAntigo);
			return construirDescricaoNova(nomeNovo);
		}
		return descricaoDaClasse;
	}

	private ObjectStreamClass construirDescricaoNova(String nomeNovo) throws ClassNotFoundException {
		return ObjectStreamClass.lookup(Class.forName(nomeNovo));
	}

	protected void renomear(String antigo, String novo) {
		mapaDeMudancasDeNome.put(antigo, novo);
	}

}
