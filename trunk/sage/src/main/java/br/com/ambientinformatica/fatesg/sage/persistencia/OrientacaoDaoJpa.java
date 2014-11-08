package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Orientacao;

@Repository("OrientacaoDao")
public class OrientacaoDaoJpa extends 
	PersistenciaJpa<Orientacao> implements OrientacaoDao {

	private static final long serialVersionUID = 1L;

	@Override
   public List<Orientacao> listarOrientacoesEstagio(Estagio estagio) {
		try {
			String jpql = "select o from Orientacao o left join fetch o.estagio e where e = :estagio";
			Query query = em.createQuery(jpql);
			query.setParameter("estagio", estagio);
			return (List<Orientacao>) query.getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<Orientacao>();
		} catch (Exception e) {
			return new ArrayList<Orientacao>();
		}
   }
}
