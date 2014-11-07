package br.com.ambientinformatica.fatesg.sage.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class Documento extends Entidade{

	@Id
	@GeneratedValue(generator = "documento_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "documento_seq", sequenceName = "documento_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	@Lob
	private byte[] dados;
	
	@OneToOne
	private Estagio estagio;

	private String nome;
	
	public Integer getId() {
		return id;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}
	
}
