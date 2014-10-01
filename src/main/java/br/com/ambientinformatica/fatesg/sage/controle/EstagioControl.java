package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.EstagioDao;

@Controller("EstagioControl")
@Scope("conversation")
public class EstagioControl {

	private Estagio estagio = new Estagio();

	@Autowired
	private EstagioDao estagioDao;

	private List<Estagio> estagios = new ArrayList<Estagio>();

	/*@PostConstruct
	public void init() {
		listar(null);
	}*/

	public void incluir(ActionEvent evt) {
		try {
			estagioDao.incluir(estagio);
			//listar(evt);
			estagio = new Estagio();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	/*public void listar() {
		try {
			estagios = estagioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}*/

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public EstagioDao getEstagioDao() {
		return estagioDao;
	}

	public void setEstagioDao(EstagioDao estagioDao) {
		this.estagioDao = estagioDao;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}
}
