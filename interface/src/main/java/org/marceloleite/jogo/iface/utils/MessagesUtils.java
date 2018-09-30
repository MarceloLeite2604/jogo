package org.marceloleite.jogo.iface.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;

@Component
public class MessagesUtils {

	@Inject
	@Named("messages")
	private ResourceBundle messageResourceBundle;

	public String obterMensagem(String nome, Object... argumentos) {
		return MessageFormat.format(messageResourceBundle.getString(nome), argumentos);
	}
}
