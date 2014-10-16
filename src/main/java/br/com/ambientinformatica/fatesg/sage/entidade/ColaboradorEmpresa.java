package br.com.ambientinformatica.fatesg.sage.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class ColaboradorEmpresa extends Entidade {

	@Id
	@GeneratedValue(generator = "colaboradorEmpresa_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "colaboradorEmpresa_seq", sequenceName = "colaboradorEmpresa_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String nome;

	private String email;

	private String telefone;

	@Column(unique=true, nullable=false)
	private String cpf;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
