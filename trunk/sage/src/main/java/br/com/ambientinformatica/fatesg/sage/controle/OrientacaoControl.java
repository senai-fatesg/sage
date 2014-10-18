package br.com.ambientinformatica.fatesg.sage.controle;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
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

	private Orientacao orientacao = new Orientacao();

	@Autowired
	private OrientacaoDao orientacaoDao;

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private EstagioDao estagioDao;

	private Aluno aluno;

	private List<Aluno> alunos = new ArrayList<Aluno>();

	private List<Estagio> estagios;

	private List<Orientacao> orientacoes = new ArrayList<Orientacao>();

	private Documento documento = new Documento();

	@Autowired
	private DocumentoDao documentoDao;

	private List<Documento> documentos = new ArrayList<Documento>();

	private StreamedContent file;

	//private static final int DEFAULT_BUFFER_SIZE = 10240;

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
	public StreamedContent getFile() {

		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream(documento.getDados().toString());
		
		file = new DefaultStreamedContent(stream, "application/pdf", ""
				+ documento.getNome());
		
		return file;
	}

	public List<Documento> listarDocumentos() {
		try {
			documentos = documentoDao.findDocumentosById();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro para lista os documentos.");
		}
		return documentos;
	}

	public void incluir(ActionEvent evt) {
		try {
			orientacaoDao.incluir(orientacao);
			listar(evt);
			orientacao = new Orientacao();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro para incluir a orientação.");
		}
	}

	public List<SelectItem> getTiposAtendimento() {
		return UtilFaces.getListEnum(EnumTipoAtendimento.values());
	}

	public void listar(ActionEvent evt) {
		try {
			orientacoes = orientacaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro para lista as orientações.");
		}
	}

	public List<Aluno> listarAlunos(String query) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			alunos = alunoDao.listarPorNome(query);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro para lista os alunos.");
		}
		return alunos;
	}

	public List<Estagio> listarEstagiosDoAluno(Aluno aluno) {
		try {
			estagios.clear();
			estagios = estagioDao.listarEstagiosDoAluno(aluno);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Houve erro para lista os estágios.");
		}
		return estagios;
	}

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
}