package org.marceloleite.jogo.iface.excecao;

public class JogoRegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public JogoRegraNegocioException(String mensagem) {
		super(mensagem);
	}

}
