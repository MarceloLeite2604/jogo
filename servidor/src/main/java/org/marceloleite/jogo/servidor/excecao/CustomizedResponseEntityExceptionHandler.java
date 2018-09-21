package org.marceloleite.jogo.servidor.excecao;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	private static final HttpStatus HTTP_STATUS_REGRA_NEGOCIO = HttpStatus.UNPROCESSABLE_ENTITY;

	@ExceptionHandler(JogoRegraNegocioException.class)
	public final ResponseEntity<Object> tratarExcecoesRegraNegocio(Exception excecao, WebRequest requisicao) {
		Map<String, Object> errorAttributes = obterErrorAttributes(requisicao, HTTP_STATUS_REGRA_NEGOCIO);
		errorAttributes.put("message", excecao.getMessage());
		return ResponseEntity.status(HTTP_STATUS_REGRA_NEGOCIO)
				.body(errorAttributes);
	}

	private Map<String, Object> obterErrorAttributes(WebRequest requisicao, HttpStatus httpStatus) {
		DefaultErrorAttributes attributes = new DefaultErrorAttributes();
		Map<String, Object> errorAttributes = attributes.getErrorAttributes(requisicao, false);
		errorAttributes.put("status", httpStatus.value());
		errorAttributes.remove("error");
		return errorAttributes;
	}
}