package br.lucasPereira.devedoresDaReceita.infraestrutura;

public class Dorminhoco {

	public void descansarSegundos(Integer tempoEmSegundos) {
		if (tempoEmSegundos > 0) {
			try {
				Thread.sleep(tempoEmSegundos * 1000);
			} catch (InterruptedException excecao) {
				excecao.printStackTrace();
			}
		}
	}

}
