package org.marceloleite.jogo.servidor.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
public class JogoRegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public JogoRegraNegocioException(String mensagem) {
		super(mensagem);
	}

}
