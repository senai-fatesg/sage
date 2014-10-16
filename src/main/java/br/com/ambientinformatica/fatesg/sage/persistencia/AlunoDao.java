package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AlunoDao extends Persistencia<Aluno> {

	List<Aluno> listarPorNome(String query);

}
