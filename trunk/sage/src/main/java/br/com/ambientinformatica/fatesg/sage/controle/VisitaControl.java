package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Visita;
import br.com.ambientinformatica.fatesg.sage.persistencia.VisitaDao;

@Controller("VisitaControl")
@Scope("conversation")
public class VisitaControl {

	private Visita visita = new Visita();
	
	@Autowired
	private VisitaDao visitaDao;
	
	private List<Visita> visitas = new ArrayList<Visita>();

	@PostConstruct
   public void init(){
		listar(null);
   }
	
	public void incluir(ActionEvent evt){
		try {
			visitaDao.incluir(visita);
			listar(evt);
         visita = new Visita();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void listar(ActionEvent evt){
		try {
			visitas = visitaDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public VisitaDao getVisitaDao() {
		return visitaDao;
	}

	public void setVisitaDao(VisitaDao visitaDao) {
		this.visitaDao = visitaDao;
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
}
