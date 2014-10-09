package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("estagioDao")
public class EstagioDaoJpa extends PersistenciaJpa<Estagio> implements EstagioDao {

	private static final long serialVersionUID = 1L;
	
	//método para buscar os estágios de cada aluno selecionado na tela de orientacao
	public List<Estagio> findEstagiosById(Aluno aluno) {
		String jpql = "SELECT e from Estagio e JOIN e.aluno a where a.id = :aluno";
		Query query = em.createQuery(jpql);
		query.setParameter("aluno", aluno.getId());
		List<Estagio> estagios = (List<Estagio>) query.getResultList();
		return estagios;
   }
}
