package br.com.ambientinformatica.fatesg.sage.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class Empresa extends Entidade {

	@Id
	@GeneratedValue(generator = "empresa_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "empresa_seq", sequenceName = "empresa_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String nome;

	private String endereco;

	@Column(unique=true, nullable=false)
	private String email;

	private String telefone;

	private String celular;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id")
	private List<ColaboradorEmpresa> supervisores = new ArrayList<ColaboradorEmpresa>();

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public List<ColaboradorEmpresa> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(List<ColaboradorEmpresa> supervisores) {
		this.supervisores = supervisores;
	}

}
