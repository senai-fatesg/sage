package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.fatesg.sage.entidade.ColaboradorEmpresa;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;

@Repository("ColaboradorEmpresaDao")
public class ColaboradorEmpresaDaoJpa extends
      PersistenciaJpa<ColaboradorEmpresa> implements ColaboradorEmpresaDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<ColaboradorEmpresa> listarPorEmpresa(Empresa empresa) {
		try {
			String jpql = "select c from ColaboradorEmpresa c left join fetch c.empresa e where e = :empresa";
			Query query = em.createQuery(jpql);
			query.setParameter("empresa", empresa);
			return (List<ColaboradorEmpresa>) query.getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<ColaboradorEmpresa>();
		} catch (Exception e) {
			return new ArrayList<ColaboradorEmpresa>();
		}
	}

}