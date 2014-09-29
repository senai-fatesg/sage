package br.com.ambientinformatica.fatesg.sage.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("empresaDao")
public class EmpresaDaoJpa extends PersistenciaJpa<Empresa> implements EmpresaDao{

   private static final long serialVersionUID = 1L;

}