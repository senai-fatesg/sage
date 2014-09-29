package br.com.ambientinformatica.fatesg.sage.entidade;

public enum EnumTipoAtendimento {

	PRIMEIRAORIENTACAO, RELATORIOPARCIAL, VISITADEESTAGIO, RELATORIOFINAL, RELATORIOEMCD, OUTROS, CONCLUIDO;

	/*
	 * PO("PRIMEIRA ORIENTAÇÃO");
	 * 
	 * private String descricao;
	 * 
	 * EnumTipoAtendimento(String descricao) { this.descricao = descricao; }
	 * 
	 * public static EnumTipoAtendimento getDescricao() { if (descricao != null)
	 * { for (EnumTipoAtendimento e : values()) { if
	 * (descricao.equalsIgnoreCase(e.name())) { return e; } } } return null; }
	 */
}