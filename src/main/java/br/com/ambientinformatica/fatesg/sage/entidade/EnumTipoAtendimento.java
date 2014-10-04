package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.ambientinformatica.util.IEnum;

public enum EnumTipoAtendimento implements IEnum{

	PRIMEIRAORIENTACAO("PRIMEIRAORIENTACAO","Primeira Orientação"), 
	RELATORIOPARCIAL("RELATORIOPARCIAL","Relatório Parcial"), 
	VISITADEESTAGIO("VISITADEESTAGIO","Visita Estágio"), 
	RELATORIOFINAL("RELATORIOFINAL","Relatório Final"), 
	RELATORIOEMCD("RELATORIOEMCD","Relatório em CD"), 
	OUTROS("OUTROS","Outros"), 
	CONCLUIDO("CONCLUIDO","Concluído");

	private String descricao;
	private String sigla;
	
	EnumTipoAtendimento(String sigla, String descricao) {
			this.descricao = descricao;
			this.sigla = sigla;
	}
	
	public static List<SelectItem> lista() {
		List<SelectItem> listaTipoAtendimento= new ArrayList<SelectItem>();
			for (EnumTipoAtendimento e : values()) {
				listaTipoAtendimento.add(new SelectItem(e.getSigla(), e.getDescricao(), e.toString()));
		}
		return listaTipoAtendimento;
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