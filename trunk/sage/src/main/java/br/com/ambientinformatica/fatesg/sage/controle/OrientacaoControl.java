package br.com.ambientinformatica.fatesg.sage.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Documento;
import br.com.ambientinformatica.fatesg.sage.entidade.EnumTipoAtendimento;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Orientacao;
import br.com.ambientinformatica.fatesg.sage.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.DocumentoDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EstagioDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.OrientacaoDao;

@Controller("OrientacaoControl")
@Scope("conversation")
public class OrientacaoControl {

	private Orientacao orientacao;

	@Autowired
	private OrientacaoDao orientacaoDao;

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private EstagioDao estagioDao;

	private Aluno aluno;

	private Estagio estagio = new Estagio();

	private List<Aluno> alunos = new ArrayList<Aluno>();

	private List<Estagio> estagios;

	private List<Orientacao> orientacoes = new ArrayList<Orientacao>();

	private Documento documento = new Documento();

	@Autowired
	private DocumentoDao documentoDao;

	private List<Documento> documentos = new ArrayList<Documento>();

	private StreamedContent file;

	@PostConstruct
	public void init() {
		orientacao = new Orientacao();
		
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
		 * Obtem o HttpServletResponse, objeto responsável pela resposta do
		 * servidor ao browser
		 */
		HttpServletResponse response = (HttpServletResponse) fc
				.getExternalContext().getResponse();

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
		response.setHeader("Content-disposition",
				"inline; filename=arquivo.pdf");
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
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	/*
	 * Download do arquivo
	 */

	public StreamedContent getRealizarDownload() {
		try {
			Documento documento = (Documento) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get("cont");
			criaArquivo(documento.getDados(), documento.getNome());

			InputStream stream = ((ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext())
					.getResourceAsStream("/documento/" + documento.getNome());
			file = new DefaultStreamedContent(stream, "application/pdf",
					documento.getNome());
			return file;
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro para baixar o documento ");
			return null;
		}
	}

	public static void criaArquivo(byte[] bytes, String nomeTemporario)
			throws Exception {
		FileOutputStream fos = null;
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();
			String arquivo = scontext.getRealPath("/documento/");

			new File(arquivo).mkdirs();

			File file = new File(scontext.getRealPath("/documento/") + "/"
					+ nomeTemporario);
			fos = new FileOutputStream(file);
			/*
			 * This logic will check whether the file exists or not. If the file
			 * is not found at the specified location it would create a new file
			 */
			if (!file.exists()) {
				file.createNewFile();
			}
			fos.write(bytes);
			fos.flush();
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ioe) {
				throw ioe;
			}
		}
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
			if (orientacao == null
					|| estagio.getProfessorOrientador().isEmpty()) {
				UtilFaces.addMensagemFaces("Favor Preencher todos os campos!");
			} else {
				orientacao.setProfessor(estagio.getProfessorOrientador());
				orientacao.setEstagio(estagio);
				orientacao.setArquivo(null);
				orientacaoDao.incluir(orientacao);
				listar(estagio);
				orientacao = new Orientacao();
				estagio = new Estagio();
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<SelectItem> getTiposAtendimento() {
		return UtilFaces.getListEnum(EnumTipoAtendimento.values());
	}

	public void listar(Estagio estagio) {
		try {
			orientacoes = orientacaoDao.listarOrientacoesEstagio(estagio);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Aluno> listarAlunos(String query) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			alunos = alunoDao.listarPorNome(query);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return alunos;
	}

	public void listarEstagiosDoAluno(SelectEvent event) {
		try {
			aluno = (Aluno) event.getObject();
			if (aluno == null) {

			} else {
				estagios = estagioDao.listarEstagiosDoAluno(aluno);
				
			}

		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// PEGA O PROFESSOR ORIENTADOR DA LISTA E PREENCHE NO CAMPO
	public void professorSelecionado(ActionEvent evt) {
		try {
			estagio = (Estagio) evt.getComponent().getAttributes()
					.get("professor");
			documentos = documentoDao.listarDocumentosEstagio(estagio);
			orientacoes = orientacaoDao.listarOrientacoesEstagio(estagio);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("orientacao.jsf");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	// GTT E STT
	public Orientacao getOrientacao() {
		return orientacao;
	}

	public void setOrientcao(Orientacao orientacao) {
		this.orientacao = orientacao;
	}

	public OrientacaoDao getOrientacaoDao() {
		return orientacaoDao;
	}

	public void setOrientacaoDao(OrientacaoDao orientacaoDao) {
		this.orientacaoDao = orientacaoDao;
	}

	public List<Orientacao> getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(List<Orientacao> orientacoes) {
		this.orientacoes = orientacoes;
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
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
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

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}
}
