package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.fatesg.sage.entidade.Orientacao;

@Repository("OrientacaoDao")
public class OrientacaoDaoJpa extends 
	PersistenciaJpa<Orientacao> implements OrientacaoDao {

	private static final long serialVersionUID = 1L;
}
