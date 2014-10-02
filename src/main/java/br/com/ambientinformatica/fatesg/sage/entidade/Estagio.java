package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ambientinformatica.fatesg.api.Aluno;

@Entity
public class Estagio {

	@Id
	@GeneratedValue(generator = "estagio_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "estagio_seq", sequenceName = "estagio_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private Empresa empresa;

	private Aluno aluno;

	@Column(name = "IDF_TIPOESTAGIO")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoEstagio tipoEstagio;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	private String professorOrientador;

	@Temporal(TemporalType.DATE)
	private Date dataMatricula;

	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraOrientacao;

	@Temporal(TemporalType.DATE)
	private Date dataRelatorioParcial;
	private Date dataRelatorioFinal;
	private Date dataRelatorioCd;

	@OneToMany
	@JoinColumn(name = "ID_DOCUMENTO")
	private List<Documento> documento;

	// TODO verificar metodo
	public void agendarVisita() {

	}

	// TODO verificar metodo
	public void montarProgramaEstagio() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public EnumTipoEstagio getTipoEstagio() {
		return tipoEstagio;
	}

	public void setTipoEstagio(EnumTipoEstagio tipoEstagio) {
		this.tipoEstagio = tipoEstagio;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getProfessorOrientador() {
		return professorOrientador;
	}

	public void setProfessorOrientador(String professorOrientador) {
		this.professorOrientador = professorOrientador;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Date getDataPrimeiraOrientacao() {
		return dataPrimeiraOrientacao;
	}

	public void setDataPrimeiraOrientacao(Date dataPrimeiraOrientacao) {
		this.dataPrimeiraOrientacao = dataPrimeiraOrientacao;
	}

	public Date getDataRelatorioParcial() {
		return dataRelatorioParcial;
	}

	public void setDataRelatorioParcial(Date dataRelatorioParcial) {
		this.dataRelatorioParcial = dataRelatorioParcial;
	}

	public Date getDataRelatorioFinal() {
		return dataRelatorioFinal;
	}

	public void setDataRelatorioFinal(Date dataRelatorioFinal) {
		this.dataRelatorioFinal = dataRelatorioFinal;
	}

	public Date getDataRelatorioCd() {
		return dataRelatorioCd;
	}

	public void setDataRelatorioCd(Date dataRelatorioCd) {
		this.dataRelatorioCd = dataRelatorioCd;
	}

	public List<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	}

}
