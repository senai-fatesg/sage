package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class Visita extends Entidade {

	@Id
	@GeneratedValue(generator = "visita_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "visita_seq", sequenceName = "visita_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@ManyToOne
	private Estagio estagio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVisita;

	private Boolean visitaRealizada;

	private String observacao;

	public Integer getId() {
		return id;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getVisitaRealizada() {
		return visitaRealizada;
	}

	public void setVisitaRealizada(Boolean visitaRealizada) {
		this.visitaRealizada = visitaRealizada;
	}

}
