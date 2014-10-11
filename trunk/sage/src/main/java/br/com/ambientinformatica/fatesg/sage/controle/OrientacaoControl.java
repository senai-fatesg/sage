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
import br.com.ambientinformatica.fatesg.sage.entidade.EnumTipoAtendimento;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Orientacao;
import br.com.ambientinformatica.fatesg.sage.persistencia.AlunoDao;
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
	
	private List<Aluno> alunos;
	private List<Estagio> estagios;

	private List<Orientacao> orientacoes = new ArrayList<Orientacao>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void incluir(ActionEvent evt) {
		try {
			orientacaoDao.incluir(orientacao);
			listar(evt);
			orientacao = new Orientacao();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<SelectItem> getTiposAtendimento() {
		return UtilFaces.getListEnum(EnumTipoAtendimento.values());
	}

	public void listar(ActionEvent evt) {
		try {
			orientacoes = orientacaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Aluno> autocomplete(String Query) {
		try {
			alunos =  alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		return alunos;
	}
	public List<Estagio> listarEstagios(Aluno aluno) {
		try {
			estagios = estagioDao.findEstagiosById(aluno);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
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

}
