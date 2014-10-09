package br.com.ambientinformatica.fatesg.sage.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.EnumTipoEstagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.sage.persistencia.EstagioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Controller("EstagioControl")
@Scope("conversation")
public class EstagioControl {

	private String txt1;

	private Estagio estagio = new Estagio();

	@Autowired
	private EstagioDao estagioDao;

	@Autowired
	private AlunoDao alunoDao;

	private List<Estagio> estagios = new ArrayList<Estagio>();

	/*
	 * @PostConstruct public void init() { listar(null); }
	 */

	public List<Aluno> completeTheme(String query) throws PersistenciaException {

		List<Aluno> allThemes = alunoDao.listar();
		List<Aluno> filteredThemes = new ArrayList<Aluno>();

		for (int i = 0; i < allThemes.size(); i++) {
			Aluno skin = allThemes.get(i);
			if (skin.getNome().toLowerCase().startsWith(query)) {
				filteredThemes.add(skin);
			}
		}

		return filteredThemes;

	}

	public void incluir(ActionEvent evt) {
		try {
			estagioDao.incluir(estagio);
			// listar(evt);
			estagio = new Estagio();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}

	}

	/*
	 * public void listar() { try { estagios = estagioDao.listar(); } catch
	 * (Exception e) { UtilFaces.addMensagemFaces(e); } }
	 */

	public List<SelectItem> getTiposEstagio() {
		return UtilFaces.getListEnum(EnumTipoEstagio.values());
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public EstagioDao getEstagioDao() {
		return estagioDao;
	}

	public void setEstagioDao(EstagioDao estagioDao) {
		this.estagioDao = estagioDao;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}
}
