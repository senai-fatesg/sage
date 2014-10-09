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
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("EmpresaControl")
@Scope("conversation")
public class EmpresaControl {
	
	private String txtNomeEmpresa;
	
	private Empresa empresa = new Empresa();
	
	@Autowired
	private EmpresaDao empresaDao;
	
	private List<Empresa> empresas = new ArrayList<Empresa>();

	@PostConstruct
   public void init(){
		listar(null);
   }
	
	public List<Empresa> autoCompleteEmpresa(String query) throws PersistenciaException {

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
	
	public void incluir(ActionEvent evt){
		try {
			empresaDao.incluir(empresa);
			listar(evt);
         empresa = new Empresa();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void listar(ActionEvent evt){
		try {
			empresas = empresaDao.listar();
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
	
	
}
