package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class Orientacao extends Entidade {

	@Id
	@GeneratedValue(generator = "orientacao_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "orientacao_seq", sequenceName = "orientacao_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtendimento;

	private String descricao;

	private String informacaoComplementar;

	@Enumerated(EnumType.STRING)
	private EnumTipoAtendimento tipoAtendimento;

	@ManyToOne
	private Estagio estagio = new Estagio();

	private String professor;

	@ManyToOne
	private Documento arquivo;

	public Integer getId() {
		return id;
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

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Documento getArquivo() {
		return arquivo;
	}

	public void setArquivo(Documento arquivo) {
		this.arquivo = arquivo;
	}

	public void setTipoAtendimento(EnumTipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	
	

}
