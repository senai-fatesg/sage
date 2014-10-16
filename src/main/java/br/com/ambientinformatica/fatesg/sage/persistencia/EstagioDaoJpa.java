package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.util.SageException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("estagioDao")
public class EstagioDaoJpa extends PersistenciaJpa<Estagio> implements EstagioDao {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Retorna todos os estagios de um determinado aluno
	 * 
	 * @param aluno
	 * @return
	 */
	@SuppressWarnings("unchecked")
   public List<Estagio> listarEstagiosDoAluno(Aluno aluno) throws SageException{
		try {
	      String jpql = "select e from Estagio e left join fetch e.aluno a where a = :aluno";
	      Query query = em.createQuery(jpql);
	      query.setParameter("aluno", aluno);
	      return (List<Estagio>) query.getResultList();
		} catch (NoResultException nre){
			return new ArrayList<Estagio>();
		} catch (Exception e) {
			return new ArrayList<Estagio>();
      }
   }
}
