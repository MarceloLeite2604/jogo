package org.marceloleite.jogo.iface.excecao.handler;

import java.util.Optional;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.marceloleite.jogo.iface.utils.FacesMessageUtils;

public class JogoExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	private static FacesMessageUtils facesMessageUtils;

	private static JogoExceptionHandler jogoExceptionHandler;

	public JogoExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		if (jogoExceptionHandler == null) {
			setJogoExceptionHandler(criarJogoExceptionHandler());
		}
		return jogoExceptionHandler;
	}

	private JogoExceptionHandler criarJogoExceptionHandler() {
		JogoExceptionHandler jogoExceptionHandler = new JogoExceptionHandler(parent.getExceptionHandler());
		Optional.ofNullable(facesMessageUtils)
				.ifPresent(jogoExceptionHandler::setFacesMessageUtils);
		return jogoExceptionHandler;
	}

	private static void setJogoExceptionHandler(JogoExceptionHandler jogoExceptionHandler) {
		JogoExceptionHandlerFactory.jogoExceptionHandler = jogoExceptionHandler;
	}

	public static void setFacesMessageUtils(FacesMessageUtils facesMessageUtils) {
		JogoExceptionHandlerFactory.facesMessageUtils = facesMessageUtils;
		Optional.ofNullable(jogoExceptionHandler)
				.ifPresent(jogoExceptionHandler -> jogoExceptionHandler.setFacesMessageUtils(facesMessageUtils));
	}
}