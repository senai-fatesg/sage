package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.VagaEstagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.VagaEstagioDao;

@Controller("VagaEstagioControl")
@Scope("conversation")
public class VagaEstagioControl {

	private VagaEstagio vagaEstagio = new VagaEstagio();

	@Autowired
	private VagaEstagioDao vagaEstagioDao;

	private List<VagaEstagio> vagaEstagios = new ArrayList<VagaEstagio>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	// TODO verificar metodo
	public void publicarVaga() {

	}

	// TODO verificar metodo Alunos List<Alunos>
	public void enviarEmailVagasDisponiveis() {

	}

	public void incluir(ActionEvent evt) {

		try {
			if (vagaEstagio == null) {
				UtilFaces.addMensagemFaces("Favor Preencher todos os campos!");
			} else {
				vagaEstagioDao.incluir(vagaEstagio);
				listar(evt);
				vagaEstagio = new VagaEstagio();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void listar(ActionEvent evt) {
		try {
			vagaEstagios = vagaEstagioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public VagaEstagio getVagaEstagio() {
		return vagaEstagio;
	}

	public void setVagaEstagio(VagaEstagio vagaEstagio) {
		this.vagaEstagio = vagaEstagio;
	}

	public VagaEstagioDao getVagaEstagioDao() {
		return vagaEstagioDao;
	}

	public void setVagaEstagioDao(VagaEstagioDao vagaEstagioDao) {
		this.vagaEstagioDao = vagaEstagioDao;
	}

	public List<VagaEstagio> getVagaEstagios() {
		return vagaEstagios;
	}

	public void setVagaEstagios(List<VagaEstagio> vagaEstagios) {
		this.vagaEstagios = vagaEstagios;
	}

}
