package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.ambientinformatica.util.IEnum;

public enum EnumStatusEstagio implements IEnum{
	
	EMANDAMENTO ("EMANDAMENTO", "Em andamento"),
	FINALIZADO ("FINALIZADO", "Finalizado");
	
	private String descricao;
	private String sigla;
	
	EnumStatusEstagio(String sigla, String descricao) {
			this.descricao = descricao;
			this.sigla = sigla;
	}
	
	public static List<SelectItem> lista() {
		List<SelectItem> listaStatusEstagio = new ArrayList<SelectItem>();
			for (EnumStatusEstagio e : values()) {
				listaStatusEstagio.add(new SelectItem(e.getSigla(), e.getDescricao(), e.toString()));
		}
		return listaStatusEstagio;
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
