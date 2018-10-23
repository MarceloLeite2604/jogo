package org.marceloleite.jogo.iface.configuracao;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceConfiguration.class);

	@Bean
	@Named("messages")
	public ResourceBundle criarResourceBundle(Locale locale) {
		LOGGER.debug("Criando ResourceBundle de messages.");
		return ResourceBundle.getBundle("locale.messages", locale);
	}

	@Bean
	public Locale criarLocale() {
		LOGGER.debug("Criando Locale.");
		return new Locale("pt", "BR");
	}
}
