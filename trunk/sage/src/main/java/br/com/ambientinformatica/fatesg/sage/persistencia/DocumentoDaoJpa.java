package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Documento;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("documentoDao")
public class DocumentoDaoJpa extends PersistenciaJpa<Documento> implements DocumentoDao {

	private static final long serialVersionUID = 1L;
	
	public List<Documento> findDocumentosById(){
		//SELECT e from Estagio e JOIN e.aluno a where a.id = :aluno"
		String jpql = "SELECT d from Documento d"; 
		Query query = em.createQuery(jpql);
		//query.setParameter("aluno", aluno.getId());
		List<Documento> documentos = (List<Documento>) query.getResultList();
		return documentos;
	}

	@Override
   public List<Documento> listarDocumentosEstagio(Estagio estagio) {
		try {
	      String jpql = "select d from Documento d left join fetch d.estagio e where e = :estagio";
	      Query query = em.createQuery(jpql);
	      query.setParameter("estagio", estagio);
	      return (List<Documento>) query.getResultList();
		} catch (NoResultException nre){
			return new ArrayList<Documento>();
		} catch (Exception e) {
			return new ArrayList<Documento>();
      }
   }
}
