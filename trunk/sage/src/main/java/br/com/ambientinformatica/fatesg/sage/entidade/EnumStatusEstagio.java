package br.com.ambientinformatica.fatesg.sage.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumStatusEstagio implements IEnum{
	
	EM_ANDAMENTO ("EM ANDAMENTO"),
	FINALIZADO ("FINALIZADO");
	
	private final String descricao;
	
	EnumStatusEstagio(String descricao) {
			this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
