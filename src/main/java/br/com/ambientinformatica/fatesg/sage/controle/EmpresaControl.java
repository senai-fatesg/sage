package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.persistencia.EmpresaDao;

@Controller("EmpresaControl")
@Scope("conversation")
public class EmpresaControl {

	private String txtNomeEmpresa;

	private boolean btnIncluir = false;

	private boolean btnAlterar = true;

	private boolean btnExcluir = true;

	private Empresa empresa = new Empresa();

	@Autowired
	private EmpresaDao empresaDao;

	private List<Empresa> empresas = new ArrayList<Empresa>();

	@PostConstruct
	public void init() {
		empresa = new Empresa();
		listar(null);
	}

	public void incluir(ActionEvent evt) {

		try {
			if (empresa == null || empresa.getNome().isEmpty()) {
				UtilFaces.addMensagemFaces("Favor Preencher todos os campos!");
			} else {
				empresa.setAtivo(true);
				empresaDao.incluir(empresa);
				listar(evt);
				empresa = new Empresa();
				btnIncluir = true;
				btnAlterar = false;
				btnExcluir = false;
				
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	public void alterar(ActionEvent evt) {
		try {
			if (empresa != null) {
				empresaDao.alterar(empresa);
				listar(evt);
				empresa = new Empresa();
				
				UtilFaces.addMensagemFaces("Empresa alterada com sucesso!");
				
				btnIncluir = false;
				btnAlterar = true;
				btnExcluir = true;
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(ActionEvent evt) {
		try {
			if (empresa != null) {
				int id = empresa.getId();
				empresa.setAtivo(false);
				empresaDao.desativarEmpresa(id);
				listar(evt);
				empresa = new Empresa();

				UtilFaces.addMensagemFaces("Empresa excluida com sucesso!");
				
				btnIncluir = false;
				btnAlterar = true;
				btnExcluir = true;
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			empresas = empresaDao.listarAtivos();
			// empresas = empresaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// PEGA A EMPRESA E PREENCHE NA TELA
	public void selecionaEmpresa(ActionEvent evt) {
		try {
			empresa = (Empresa) evt.getComponent().getAttributes().get("empresa");
			btnIncluir = true;
			btnAlterar = false;
			btnExcluir = false;
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public String getTxtNomeEmpresa() {
		return txtNomeEmpresa;
	}

	public void setTxtNomeEmpresa(String txtNomeEmpresa) {
		this.txtNomeEmpresa = txtNomeEmpresa;
	}

	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}

	public boolean isBtnIncluir() {
		return btnIncluir;
	}

	public void setBtnIncluir(boolean btnIncluir) {
		this.btnIncluir = btnIncluir;
	}

	public boolean isBtnAlterar() {
		return btnAlterar;
	}

	public void setBtnAlterar(boolean btnAlterar) {
		this.btnAlterar = btnAlterar;
	}

	public boolean isBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(boolean btnExcluir) {
		this.btnExcluir = btnExcluir;
	}

}
