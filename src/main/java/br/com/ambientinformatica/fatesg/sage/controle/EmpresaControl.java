package br.com.ambientinformatica.fatesg.sage.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Documento;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.persistencia.DocumentoDao;
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

	private String destino = "C:\\AreaDeTrabalho\\Aplicativos\\";

	private Documento documento = new Documento();

	@Autowired
	private DocumentoDao documentoDao;

	private List<Documento> documentos = new ArrayList<Documento>();

	@PostConstruct
	public void init() {
		listar(null);
		listarDocumentos();
	}

	// Salvar arquivo no banco de dados
	public void upload(FileUploadEvent event) throws PersistenciaException {

		try {
			documento.setNome(event.getFile().getFileName());
			documento.setArquivo(event.getFile().getContents());
			documentoDao.incluir(documento);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().getMessages(e.getMessage());
		} finally {

		}
		FacesContext.getCurrentInstance().addMessage(
				"Mensagem", new FacesMessage("Sucesso! " + event.getFile().getFileName() + " enviado."));
	}

	// Salvar arquivo no diretorio
	public void copyFile(String fileName, InputStream in)
			throws PersistenciaException {
		try {
			OutputStream out = new FileOutputStream(
					new File(destino + fileName));
			int read = 0;
			byte[] bytes = new byte[in.read()];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
			FacesMessage msg = new FacesMessage("Novo arquivo criado");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public List<Documento> listarDocumentos() {
		try {
			documentos = documentoDao.findDocumentosById();
		} catch (Exception e) {
			// handle exception
			UtilFaces.addMensagemFaces(e);
		}
		return documentos;
	}

	public void incluir(ActionEvent evt) {
		try {
			empresaDao.incluir(empresa);
			listar(evt);
			empresa = new Empresa();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
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

	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public DocumentoDao getDocumentoDao() {
		return documentoDao;
	}

	public void setDocumentoDao(DocumentoDao documentoDao) {
		this.documentoDao = documentoDao;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}
