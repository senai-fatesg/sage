package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.ColaboradorEmpresa;
import br.com.ambientinformatica.fatesg.sage.persistencia.ColaboradorEmpresaDao;

@Controller("ColaboradorEmpresaControl")
@Scope("conversation")
public class ColaboradorEmpresaControl {

	private ColaboradorEmpresa colaborador = new ColaboradorEmpresa();

	@Autowired
	private ColaboradorEmpresaDao colaboradorDao;

	private List<ColaboradorEmpresa> colaboradores = new ArrayList<ColaboradorEmpresa>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void incluir(ActionEvent evt) {
		try {
			colaboradorDao.incluir(colaborador);
			listar(evt);
			colaborador = new ColaboradorEmpresa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
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
