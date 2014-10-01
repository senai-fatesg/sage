package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Visita;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("VisitaDao")
public class VisitaDaoJpa extends PersistenciaJpa<Visita> implements VisitaDao {

	private static final long serialVersionUID = 1L;

}
