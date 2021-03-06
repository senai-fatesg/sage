package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

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

@Controller("EstagioControl")
@Scope("conversation")
public class EstagioControl {

	private Estagio estagio = new Estagio();

	@Autowired
	private EstagioDao estagioDao;

	@Autowired
	private EmpresaDao empresaDao;

	@Autowired
	private AlunoDao alunoDao;

	private List<Estagio> estagios = new ArrayList<Estagio>();

	private EnumTipoEstagio tipoEstagio = EnumTipoEstagio.OBRIGATORIO;
	
	public List<SelectItem> getTiposEstagio(){
		return UtilFaces.getListEnum(EnumTipoEstagio.values());
	}
	
	//TODO verificar metodo
	public void agendarVisita(ActionEvent evt) {

	}

	//TODO verificar metodo
	public void montarProgramaEstagio(ActionEvent evt) {

	}
	
	@PostConstruct
	public void init() {
		estagio = new Estagio();
	}

	public void listar(ActionEvent evt) {
		try {
			estagios = estagioDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Aluno> listarAlunos(String query) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			alunos = alunoDao.listarPorNome(query);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro ao listar os alunos!");
		}
		return alunos;
	}

	public List<Empresa> listarEmpresas(String query) {
		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			empresas = empresaDao.listarPorNome(query);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro ao listar as empresas!");
		}
		return empresas;
	}

	public void incluir(ActionEvent evt) {
		try {
			if(estagio == null){
				UtilFaces.addMensagemFaces("Favor Preencher todos os campos!");
			}else{
				estagio.setTipoEstagio(tipoEstagio);
				estagioDao.alterar(estagio);
				estagio = new Estagio();
				
				UtilFaces.addMensagemFaces("Estágio Incluido com sucesso!");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro ao cadastrar o estágio!");
		}
		
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

	public EnumTipoEstagio getTipoEstagio() {
		return tipoEstagio;
	}

	public void setTipoEstagio(EnumTipoEstagio tipoEstagio) {
		this.tipoEstagio = tipoEstagio;
	}

}
