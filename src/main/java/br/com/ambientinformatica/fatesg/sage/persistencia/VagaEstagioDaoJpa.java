package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.VagaEstagio;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("vagaEstagioDao")
public class VagaEstagioDaoJpa extends PersistenciaJpa<VagaEstagio> implements
		VagaEstagioDao {
	private static final long serialVersionUID = 1L;

}
