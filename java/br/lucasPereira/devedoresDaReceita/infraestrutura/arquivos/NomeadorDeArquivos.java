package br.lucasPereira.devedoresDaReceita.infraestrutura.arquivos;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class NomeadorDeArquivos {

	private static final String FORMATO_DE_DATA = "Y.MM.dd";
	private static final String FORMATO_DE_HORARIO = "HH.mm";
	private static final String FORMATO_DO_NOME = "%s/%s_%s_%s.%s";

	private String diretorio;
	private String prefixo;
	private SimpleDateFormat formatadorDeData;
	private SimpleDateFormat formatadorDeHorarario;

	public NomeadorDeArquivos(String diretorio, String prefixo) {
		this.diretorio = diretorio;
		this.prefixo = prefixo;
		formatadorDeData = new SimpleDateFormat(FORMATO_DE_DATA);
		formatadorDeHorarario = new SimpleDateFormat(FORMATO_DE_HORARIO);
	}

	public final String obterNomeComDataHorarioAtual() {
		GregorianCalendar calendario = new GregorianCalendar();
		Date agora = calendario.getTime();
		String dataFormatada = formatadorDeData.format(agora);
		String horarioFormatado = formatadorDeHorarario.format(agora);
		return String.format(FORMATO_DO_NOME, diretorio, prefixo, dataFormatada, horarioFormatado, diretorio);
	}

	public final void criarDiretorio() {
		new File(diretorio).mkdirs();
	}

}
