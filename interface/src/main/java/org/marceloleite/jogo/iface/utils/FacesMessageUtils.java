package org.marceloleite.jogo.iface.utils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.marceloleite.jogo.iface.excecao.SeveridadeMensagem;
import org.marceloleite.jogo.iface.excecao.handler.JogoExceptionHandlerFactory;
import org.springframework.stereotype.Component;

@Component
public class FacesMessageUtils {

	@Inject
	private MessagesUtils messagesUtils;
	
	@PostConstruct
	public void postConstruct() {
		JogoExceptionHandlerFactory.setFacesMessageUtils(this);
	}
	
	public FacesMessage criarMensagemBundle(SeveridadeMensagem severidadeMensagem, String codigoMensagem, Object...argumentos) {
		String sumario = messagesUtils.obterMensagem(severidadeMensagem.getCodigoMessagesSumario());
		String mensagem = messagesUtils.obterMensagem(codigoMensagem, argumentos);
		return new FacesMessage(severidadeMensagem.getSeverity(), sumario, mensagem);
	}
	
	public FacesMessage criarMensagem(SeveridadeMensagem severidadeMensagem, String mensagem) {
		String sumario = messagesUtils.obterMensagem(severidadeMensagem.getCodigoMessagesSumario());
		return new FacesMessage(severidadeMensagem.getSeverity(), sumario, mensagem);
	}
}
