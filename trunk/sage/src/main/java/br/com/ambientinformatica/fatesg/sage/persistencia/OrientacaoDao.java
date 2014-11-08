package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.entidade.Orientacao;

public interface OrientacaoDao extends Persistencia<Orientacao>{

	List<Orientacao> listarOrientacoesEstagio(Estagio estagio);

}
