package br.com.ambientinformatica.fatesg.sage.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
public class Documento {

	@Id
	@GeneratedValue(generator = "documento_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "documento_seq", sequenceName = "documento_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	
	private byte[] arquivo;

	public Integer getId() {
		return id;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	
}
