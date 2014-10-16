package br.com.ambientinformatica.fatesg.sage.controle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Documento;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.persistencia.DocumentoDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EmpresaDao;

@Controller("EmpresaControl")
@Scope("conversation")
public class EmpresaControl {

	private String txtNomeEmpresa;

	private Empresa empresa = new Empresa();

	@Autowired
	private EmpresaDao empresaDao;

	private List<Empresa> empresas = new ArrayList<Empresa>();

	private Documento documento = new Documento();

	@Autowired
	private DocumentoDao documentoDao;

	private List<Documento> documentos = new ArrayList<Documento>();

	private StreamedContent file;

	@PostConstruct
	public void init() {
		listar(null);
		listarDocumentos();
	}

	/*
	 * Salvar arquivo no banco de dados
	 */
	public void upload(FileUploadEvent event) {
		try {
			documento.setNome(event.getFile().getFileName());
			documento.setDados(event.getFile().getContents());
			documentoDao.incluir(documento);
		} catch (Exception e) {
			UtilFaces
			      .addMensagemFaces("Houve um erro ao fazer o Upload do Arquivo.");
		}
		UtilFaces.addMensagemFaces("Arquivo carregado com sucesso.");
	}


	public void visualizarPdf() {
		FacesContext fc = FacesContext.getCurrentInstance();

		/*
		 *  Obtem o HttpServletResponse, objeto responsável pela resposta do 
		 *  servidor ao browser
		 */
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

		// Limpa o buffer do response
		response.reset();

		// Seta o tipo de conteudo no cabecalho da resposta. No caso, indica que
		// o conteudo sera um documento pdf.
		response.setContentType("application/pdf");

		// Seta o tamanho do conteudo no cabecalho da resposta. No caso, o
		// tamanho em bytes do pdf
		response.setContentLength(documento.getDados().length);

		// Seta o nome do arquivo e a disposição: "inline" abre no próprio
		// navegador
		// Mude para "attachment" para indicar que deve ser feito um download
		response.setHeader("Content-disposition", "inline; filename=arquivo.pdf");
		try {

			// Envia o conteudo do arquivo PDF para o response
			response.getOutputStream().write(documento.getDados());

			// Descarrega o conteudo do stream, forçando a escrita de qualquer
			// byte ainda em buffer
			response.getOutputStream().flush();

			// Fecha o stream, liberando seus recursos
			response.getOutputStream().close();

			// Sinaliza ao JSF que a resposta HTTP para este pedido já foi
			// gerada
			fc.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Download do arquivo
	public StreamedContent getFile() {
		InputStream stream = ((ServletContext) FacesContext.getCurrentInstance()
		      .getExternalContext().getContext()).getResourceAsStream(documento
		      .getDados().toString());
		file = new DefaultStreamedContent(stream, "application/pdf", ""
		      + documento.getNome());
		return file;
	}

	public List<Documento> listarDocumentos() {
		try {
			documentos = documentoDao.findDocumentosById();
		} catch (Exception e) {
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
