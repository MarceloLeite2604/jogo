package org.marceloleite.jogo.iface.excecao;

public class JogoSistemaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JogoSistemaException(String message) {
		super(message);
	}

	public JogoSistemaException(Throwable cause) {
		super(cause);
	}

	public JogoSistemaException(String message, Throwable cause) {
		super(message, cause);
	}
}
