package br.com.ambientinformatica.fatesg.sage.entidade;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoEstagio implements IEnum{
	
	OBRIGATORIO ("Estágio obrigatório"),
	NAO_OBRIGATORIO ("Estágio não obrigatório"), 
	DISPENSA ("Dispensa");
	
	private final String descricao;
	
	EnumTipoEstagio(String descricao) {
			this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
