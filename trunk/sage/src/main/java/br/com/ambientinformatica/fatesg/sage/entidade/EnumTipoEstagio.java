package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoEstagio implements IEnum{
	
	ESTAGIOOBRIGATORIO ("ESTAGIOOBRIGATORIO", "Estágio obrigatório"),
	ESTAGIONAOOBRIGATORIO ("ESTAGIONAOOBRIGATORIO", "Estágio não obrigatório"), 
	DISPENSA ("DISPENSA", "Dispensa");
	
	private String descricao;
	private String sigla;
	
	EnumTipoEstagio(String sigla, String descricao) {
			this.descricao = descricao;
			this.sigla = sigla;
	}
	
	public static List<SelectItem> lista() {
		List<SelectItem> listaTipoEstagio = new ArrayList<SelectItem>();
			for (EnumTipoEstagio e : values()) {
				listaTipoEstagio.add(new SelectItem(e.getSigla(), e.getDescricao(), e.toString()));
		}
		return listaTipoEstagio;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

}
