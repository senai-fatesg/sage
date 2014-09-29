package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orientacao {

	@Id
	@GeneratedValue(generator = "orientacao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "orientacao_seq", sequenceName = "orientacao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtendimento;
	
	private String descricao;
	
	private String informacaoComplementar;
	
	private EnumTipoAtendimento tipoAtendimento;
	
	private byte[] arquivo;

	// TODO Verificar dados dos aluno
	// TODO Verificar professor orientador 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInformacaoComplementar() {
		return informacaoComplementar;
	}

	public void setInformacaoComplementar(String informacaoComplementar) {
		this.informacaoComplementar = informacaoComplementar;
	}

	public EnumTipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(EnumTipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	
}
