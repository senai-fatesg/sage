package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Documento;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("documentoDao")
public class DocumentoDaoJpa extends PersistenciaJpa<Documento> implements DocumentoDao {

	private static final long serialVersionUID = 1L;
}
