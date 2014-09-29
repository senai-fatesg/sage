package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.fatesg.sage.entidade.ColaboradorEmpresa;

@Repository("ColaboradorEmpresaDao")
public class ColaboradorEmpresaDaoJpa extends
      PersistenciaJpa<ColaboradorEmpresa> implements ColaboradorEmpresaDao {

	private static final long serialVersionUID = 1L;

}