package br.com.ambientinformatica.fatesg.sage.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoAtendimento implements IEnum {

	PRIMEIRA_ORIENTACAO("Primeira Orientação"), 
	RELATORIO_PARCIAL("Relatório Parcial"), 
	VISITA_ESTAGIO("Visita Estágio"), 
	RELATORIO_FINAL("Relatório Final"), 
	RELATORIO_CD("Relatório em CD"), 
	OUTROS("Outros"), 
	CONCLUIDO("Concluído");

	private final String descricao;

	EnumTipoAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}