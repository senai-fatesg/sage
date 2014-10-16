package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

@Repository("empresaDao")
public class EmpresaDaoJpa extends PersistenciaJpa<Empresa> implements
      EmpresaDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> listarPorNome(String nome) {
		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			String sql = "select e from Empresa e where 1 = 1";
			if (nome != null) {
				sql += " and upper(e.nome) like upper(:nome)";
			}
			Query query = em.createQuery(sql);
			if (nome != null) {
				query.setParameter("nome", "%" + nome + "%");
			}
			empresas = query.getResultList();
		} catch (Exception e) {
			UtilLog.getLog().error(e.getMessage(), e);
		}
		return empresas;
	}
}
