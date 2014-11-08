package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.sage.entidade.ColaboradorEmpresa;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;

public interface ColaboradorEmpresaDao extends Persistencia<ColaboradorEmpresa> {

	List<ColaboradorEmpresa> listarPorEmpresa(Empresa empresa);

}
