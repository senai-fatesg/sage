package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.entidade.EnumTipoEstagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EmpresaDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EstagioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("EstagioControl")
@Scope("conversation")
public class EstagioControl {

	private Estagio estagio = new Estagio();

	private Aluno aluno = new Aluno();

	private Empresa empresa = new Empresa();

	@Autowired
	private EstagioDao estagioDao;

	@Autowired
	private EmpresaDao empresaDao;

	@Autowired
	private AlunoDao alunoDao;

	private List<Estagio> estagios = new ArrayList<Estagio>();

	@PostConstruct
	public void init() {
		listar(null);
	}
	
	public void listar(ActionEvent evt) {
		try {
			estagios = estagioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public List<Aluno> autoCompleteAluno(String query)
	      throws PersistenciaException {

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

	public List<Empresa> autoCompleteEmpresa(String query)
	      throws PersistenciaException {

		List<Empresa> empresas = empresaDao.listar();
		List<Empresa> filtrarEmpresas = new ArrayList<Empresa>();

		for (int i = 0; i < empresas.size(); i++) {
			Empresa empresa = empresas.get(i);
			if (empresa.getNome().toLowerCase().startsWith(query)) {
				filtrarEmpresas.add(empresa);
			}
		}
		return filtrarEmpresas;
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

	public void carregaAluno(SelectEvent event) {
		setAluno((Aluno) event.getObject());
	}

	public void carregaEstagio(SelectEvent event) {
		setEstagio((Estagio) event.getObject());
	}

	public void carregaEmpresa(SelectEvent event) {
		setEmpresa((Empresa) event.getObject());
	}

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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		getEstagio().setAluno(aluno);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		getEstagio().setEmpresa(empresa);
	}

}
