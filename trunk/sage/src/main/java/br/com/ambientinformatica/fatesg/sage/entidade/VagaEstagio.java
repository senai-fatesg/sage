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
public class VagaEstagio {

	@Id
	@GeneratedValue(generator = "vagaestagio_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "vagaestagio_seq", sequenceName = "vagaestagio_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;

	private String[] destinatario;

	private String remetente;
	
	private String assunto;
	
	private String mensagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String[] getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String[] destinatario) {
		this.destinatario = destinatario;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	// TODO verificar metodo
	public void publicarVaga(){
		
	}
	
	// TODO verificar metodo Alunos List<Alunos>
	public void enviarEmailVagasDisponiveis(){

	}
}
