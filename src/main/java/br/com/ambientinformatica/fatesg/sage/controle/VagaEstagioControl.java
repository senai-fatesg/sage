package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.exolab.castor.types.DateTime;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.entidade.VagaEstagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.EmpresaDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.VagaEstagioDao;

@Controller("VagaEstagioControl")
@Scope("conversation")
public class VagaEstagioControl {

	private VagaEstagio vagaEstagio = new VagaEstagio();

	@Autowired
	private VagaEstagioDao vagaEstagioDao;
	
	@Autowired
	private EmpresaDao empresaDao;

	private List<VagaEstagio> vagas = new ArrayList<VagaEstagio>();

	@PostConstruct
	public void init() {
		//listar(null);
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
	
	public void listarVagas(SelectEvent evt) {
		try {
			vagas = vagaEstagioDao.listarPorEmpresa(vagaEstagio.getEmpresa());
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro ao listar os colaboradores!");
		}
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
				Date d = new Date();
				vagaEstagio.setDataPublicacao(d);
				vagaEstagioDao.incluir(vagaEstagio);
				listar(evt);
				vagaEstagio = new VagaEstagio();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}
	
	public void selecionarVaga(ActionEvent evt) {
		try {
			vagaEstagio = (VagaEstagio) evt.getComponent().getAttributes()
					.get("vaga");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("vagaestagio.jsf");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	

	public void listar(ActionEvent evt) {
		try {
			vagas = vagaEstagioDao.listar();
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

	public List<VagaEstagio> getVagas() {
		return vagas;
	}

	public void setVagas(List<VagaEstagio> vagaEstagios) {
		this.vagas = vagaEstagios;
	}

}
