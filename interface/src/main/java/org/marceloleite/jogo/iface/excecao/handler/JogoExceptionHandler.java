package org.marceloleite.jogo.iface.excecao.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.marceloleite.jogo.iface.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.iface.excecao.JogoSistemaException;
import org.marceloleite.jogo.iface.excecao.SeveridadeMensagem;
import org.marceloleite.jogo.iface.utils.FacesMessageUtils;

public class JogoExceptionHandler extends ExceptionHandlerWrapper {

	private static final String ID_MESSAGES_ERRO = "messagesGerais";

	private static final String NOME_MENSAGEM_ERRO_SISTEMA = "Global.SystemError";

	private static final List<Class<? extends Throwable>> CLASSES_EXCECOES_TRATADAS = new ArrayList<>();

	static {
		CLASSES_EXCECOES_TRATADAS.add(JogoSistemaException.class);
		CLASSES_EXCECOES_TRATADAS.add(JogoRegraNegocioException.class);
	}

	private FacesMessageUtils facesMessageUtils;

	private ExceptionHandler wrapped;
	
	public void setFacesMessageUtils(FacesMessageUtils facesMessageUtils) {
		this.facesMessageUtils = facesMessageUtils;
	}

	public JogoExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() {
		Iterator<ExceptionQueuedEvent> iterador = getUnhandledExceptionQueuedEvents().iterator();

		while (iterador.hasNext()) {
			ExceptionQueuedEvent event = iterador.next();
			ExceptionQueuedEventContext eventContext = (ExceptionQueuedEventContext) event.getSource();

			Throwable throwable = buscarExcecaoConhecida(eventContext.getException());

			if (throwable != null) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, elaborarFacesMessage(throwable));
				iterador.remove();
			}
		}
		getWrapped().handle();
	}

	private FacesMessage elaborarFacesMessage(Throwable throwable) {
		if (throwable instanceof JogoSistemaException) {
			return facesMessageUtils.criarMensagemBundle(SeveridadeMensagem.ERRO, NOME_MENSAGEM_ERRO_SISTEMA);
		} else {
			JogoRegraNegocioException jogoRegraNegocioException = (JogoRegraNegocioException) throwable;
			return facesMessageUtils.criarMensagem(SeveridadeMensagem.ERRO, jogoRegraNegocioException.getMessage());
		}
	}

	private Throwable buscarExcecaoConhecida(Throwable throwable) {
		Throwable throwableAnalisado = throwable;
		while (throwableAnalisado != null && !CLASSES_EXCECOES_TRATADAS.contains(throwableAnalisado.getClass())) {
			throwableAnalisado = throwableAnalisado.getCause();
		}
		return throwableAnalisado;
	}
}