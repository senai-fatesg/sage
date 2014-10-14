package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;

@Entity
public class Estagio {

	@Id
	@GeneratedValue(generator = "estagio_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "estagio_seq", sequenceName = "estagio_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "IDF_EMPRESA")
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "IDF_ALUNO")
	private Aluno aluno;

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
	
	@Temporal(TemporalType.DATE)
	private Date dataRelatorioFinal;
	
	@Temporal(TemporalType.DATE)
	private Date dataRelatorioCd;
	
	@Enumerated(EnumType.STRING)
	private EnumStatusEstagio statusEstagio;
	
	@ManyToMany
	@JoinColumn(name = "IDF_DOCUMENTO")
	private List<Documento> documentos;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoEstagio tipoEstagio;

	// TODO verificar metodo
	public void agendarVisita() {

	}

	// TODO verificar metodo
	public void montarProgramaEstagio() {

	}

	public Integer getId() {
		return id;
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

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}
