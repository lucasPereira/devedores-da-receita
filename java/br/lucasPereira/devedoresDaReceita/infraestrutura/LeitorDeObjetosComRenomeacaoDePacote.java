package br.lucasPereira.devedoresDaReceita.infraestrutura;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashMap;
import java.util.Map;

public class LeitorDeObjetosComRenomeacaoDePacote extends ObjectInputStream {

	private Map<String, String> mapaDeMudancasDeNome;

	protected LeitorDeObjetosComRenomeacaoDePacote(InputStream entrada) throws IOException, SecurityException {
		super(entrada);
		mapaDeMudancasDeNome = new HashMap<>();
	}

	protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
		ObjectStreamClass lido = super.readClassDescriptor();
		String nomeAntigo = lido.getName();
		if (mapaDeMudancasDeNome.containsKey(nomeAntigo)) {
			String nomeNovo = mapaDeMudancasDeNome.get(nomeAntigo);
			return ObjectStreamClass.lookup(Class.forName(lido.getName().replace(nomeAntigo, nomeNovo)));
		}
		return lido;
	}

	protected void renomear(String antigo, String novo) {
		mapaDeMudancasDeNome.put(antigo, novo);
	}

}
