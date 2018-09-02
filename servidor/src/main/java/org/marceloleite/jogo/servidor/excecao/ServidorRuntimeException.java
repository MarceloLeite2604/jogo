package org.marceloleite.jogo.servidor.excecao;

public class ServidorRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ServidorRuntimeException(String mensagem) {
		super(mensagem);
	}

}
