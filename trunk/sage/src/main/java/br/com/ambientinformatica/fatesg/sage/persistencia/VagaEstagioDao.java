package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.fatesg.sage.entidade.VagaEstagio;

public interface VagaEstagioDao extends Persistencia<VagaEstagio> {

	List<VagaEstagio> listarPorEmpresa(Empresa empresa);

}
