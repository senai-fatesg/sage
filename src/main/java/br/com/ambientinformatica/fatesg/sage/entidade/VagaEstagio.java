package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class VagaEstagio extends Entidade {

	@Id
	@GeneratedValue(generator = "vagaestagio_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "vagaestagio_seq", sequenceName = "vagaestagio_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@ManyToOne
	private Empresa empresa;

	private String requisito;

	private Integer cargaHorario;

	private String diasSemana;

	private String horario;

	private long codigoVaga;

	private String beneficios;

	private String atividades;

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getRequisito() {
		return requisito;
	}

	public void setRequisito(String requisito) {
		this.requisito = requisito;
	}

	public Integer getCargaHorario() {
		return cargaHorario;
	}

	public void setCargaHorario(Integer cargaHorario) {
		this.cargaHorario = cargaHorario;
	}

	public String getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public long getCodigoVaga() {
		return codigoVaga;
	}

	public void setCodigoVaga(long codigoVaga) {
		this.codigoVaga = codigoVaga;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
