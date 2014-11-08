package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.ColaboradorEmpresa;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.persistencia.ColaboradorEmpresaDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EmpresaDao;

@Controller("ColaboradorEmpresaControl")
@Scope("conversation")
public class ColaboradorEmpresaControl {

	private ColaboradorEmpresa colaborador = new ColaboradorEmpresa();

	@Autowired
	private ColaboradorEmpresaDao colaboradorDao;
	
	@Autowired
	private EmpresaDao empresaDao;

	private List<ColaboradorEmpresa> colaboradores = new ArrayList<ColaboradorEmpresa>();

	@PostConstruct
	public void init() {
		//listar(null);
	}

	public void incluir(ActionEvent evt) {
		try {
			if (colaborador == null || colaborador.getNome().isEmpty()) {
				UtilFaces.addMensagemFaces("Favor Preencher todos os campos!");
			} else {
				colaboradorDao.incluir(colaborador);
				listaColaboradores(colaborador.getEmpresa());
				colaborador = new ColaboradorEmpresa();
				
				UtilFaces.addMensagemFaces("Colaborador cadastrado com sucesso!");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listaColaboradores(SelectEvent evt) {
		try {
			colaboradores = colaboradorDao.listarPorEmpresa(colaborador.getEmpresa());
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro ao listar os colaboradores!");
		}
	}
	public void listaColaboradores(Empresa emp) {
		try {
			colaboradores = colaboradorDao.listarPorEmpresa(emp);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro ao listar os colaboradores!");
		}
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

	public ColaboradorEmpresa getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEmpresa colaborador) {
		this.colaborador = colaborador;
	}

	public ColaboradorEmpresaDao getColaboradorDao() {
		return colaboradorDao;
	}

	public void setColaboradorDao(ColaboradorEmpresaDao colaboradorDao) {
		this.colaboradorDao = colaboradorDao;
	}

	public List<ColaboradorEmpresa> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<ColaboradorEmpresa> colaboradores) {
		this.colaboradores = colaboradores;
	}

}
