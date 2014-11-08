package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.entidade.VagaEstagio;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("vagaEstagioDao")
public class VagaEstagioDaoJpa extends PersistenciaJpa<VagaEstagio> implements
      VagaEstagioDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<VagaEstagio> listarPorEmpresa(Empresa empresa) {
		try {
			String jpql = "select v from VagaEstagio v left join fetch v.empresa e where e = :empresa";
			Query query = em.createQuery(jpql);
			query.setParameter("empresa", empresa);
			return (List<VagaEstagio>) query.getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<VagaEstagio>();
		} catch (Exception e) {
			return new ArrayList<VagaEstagio>();
		}
	}

}
