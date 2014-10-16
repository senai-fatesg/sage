package br.com.ambientinformatica.fatesg.sage.util;

import java.util.List;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

public class SageException extends PersistenciaException {

	private static final long serialVersionUID = 1L;

	public SageException() {
		super();
	}

	public SageException(List<String> listaMensagens) {
		super(listaMensagens);
	}

	public SageException(String message, Throwable cause) {
		super(message, cause);
	}

	public SageException(String message) {
		super(message);
	}

	public SageException(Throwable cause) {
		super(cause);
	}
}
