package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class NomeadorDataHorario extends NomeadorAbstrato implements Nomeador {

	private static final String FORMATO_DE_DATA = "Y.MM.dd";
	private static final String FORMATO_DE_HORARIO = "HH.mm";
	private static final String FORMATO_DO_NOME = "%s/%s_%s_%s.%s";

	private String prefixo;
	private SimpleDateFormat formatadorDeData;
	private SimpleDateFormat formatadorDeHorarario;

	public NomeadorDataHorario(Nomeador nomeador, String prefixo) {
		super(nomeador);
		this.prefixo = prefixo;
		this.formatadorDeData = new SimpleDateFormat(FORMATO_DE_DATA);
		this.formatadorDeHorarario = new SimpleDateFormat(FORMATO_DE_HORARIO);
	}

	@Override
	public final String obterNome() {
		GregorianCalendar calendario = new GregorianCalendar();
		Date agora = calendario.getTime();
		String dataFormatada = formatadorDeData.format(agora);
		String horarioFormatado = formatadorDeHorarario.format(agora);
		return String.format(FORMATO_DO_NOME, obterDiretorio(), prefixo, dataFormatada, horarioFormatado, obterDiretorio());
	}

}
