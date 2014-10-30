package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.sage.entidade.Empresa;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface EmpresaDao extends Persistencia<Empresa>{

	List<Empresa> listarPorNome(String nome);
	
	List<Empresa> listarAtivos();
	
	void desativarEmpresa(int id);
	
}

