package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("estagioDao")
public class EstagioDaoJpa extends PersistenciaJpa<Estagio> implements EstagioDao {

	private static final long serialVersionUID = 1L;
}
