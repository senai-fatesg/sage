package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

@Repository("alunoDao")
public class AlunoDaoJpa extends PersistenciaJpa<Aluno> implements AlunoDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listarPorNome(String nome) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			String sql = "select a from Aluno a where 1 = 1";
			if (nome != null) {
				sql += " and upper(a.nome) like upper(:nome)";
			}
			Query query = em.createQuery(sql);
			if (nome != null) {
				query.setParameter("nome", "%" + nome + "%");
			}
			alunos = query.getResultList();
		} catch (Exception e) {
			UtilLog.getLog().error(e.getMessage(), e);
		}
		return alunos;
	}

}