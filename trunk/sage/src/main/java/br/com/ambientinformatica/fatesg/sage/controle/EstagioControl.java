package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.EnumTipoEstagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EstagioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("EstagioControl")
@Scope("conversation")
public class EstagioControl {

	private String txtNomeAluno;
	private String txtEmailAluno =  "";

	private Estagio estagio = new Estagio();
	private Aluno aluno = new Aluno();

	@Autowired
	private EstagioDao estagioDao;

	@Autowired
	private AlunoDao alunoDao;

	private List<Estagio> estagios = new ArrayList<Estagio>();

	/*
	 * @PostConstruct public void init() { listar(null); }
	 */

	public List<Aluno> autoCompleteAluno(String query) throws PersistenciaException {

		List<Aluno> alunos = alunoDao.listar();
		List<Aluno> filtrarAlunos = new ArrayList<Aluno>();

		for (int i = 0; i < alunos.size(); i++) {
			Aluno aluno = alunos.get(i);
			if (aluno.getNome().toLowerCase().startsWith(query)) {
				filtrarAlunos.add(aluno);
			}
		}

		return filtrarAlunos;

	}
	
	public void preencherAluno(){
		//setTxtEmailAluno("Hellison.oliveira@gmail");
	}

	public void incluir(ActionEvent evt) {
		try {
			estagioDao.incluir(estagio);
			// listar(evt);
			estagio = new Estagio();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public void carregaAluno(SelectEvent event){
		setAluno((Aluno)event.getObject());
		
	}

	/*
	 * public void listar() { try { estagios = estagioDao.listar(); } catch
	 * (Exception e) { UtilFaces.addMensagemFaces(e); } }
	 */

	public List<SelectItem> getTiposEstagio() {
		return UtilFaces.getListEnum(EnumTipoEstagio.values());
	}

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

	public String getTxtNomeAluno() {
		return txtNomeAluno;
	}

	public void setTxtNomeAluno(String txtNomeAluno) {
		this.txtNomeAluno = txtNomeAluno;
	}

	public String getTxtEmailAluno() {
		return txtEmailAluno;
	}

	public void setTxtEmailAluno(String txtEmailAluno) {
		this.txtEmailAluno = txtEmailAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		getEstagio().setAluno(aluno);
	}

}
