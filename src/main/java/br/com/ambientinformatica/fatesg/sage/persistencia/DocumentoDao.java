package br.com.ambientinformatica.fatesg.sage.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.sage.entidade.Aluno;
import br.com.ambientinformatica.fatesg.sage.entidade.Documento;
import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface DocumentoDao extends Persistencia<Documento> {

	List<Documento> findDocumentosById();

	List<Documento> listarDocumentosEstagio(Estagio estagio);
}
