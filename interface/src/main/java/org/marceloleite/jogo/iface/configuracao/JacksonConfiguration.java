package org.marceloleite.jogo.iface.configuracao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.SpringHandlerInstantiator;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

@Configuration
public class JacksonConfiguration {

	@Bean
	public HandlerInstantiator criarHandlerInstantiator(ApplicationContext applicationContext) {
	    return new SpringHandlerInstantiator(applicationContext.getAutowireCapableBeanFactory());
	}

	@Bean
	public Jackson2ObjectMapperBuilder criarJackson2ObjectMapperBuilder(HandlerInstantiator handlerInstantiator) {
	    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
	    jackson2ObjectMapperBuilder.handlerInstantiator(handlerInstantiator);
	    return jackson2ObjectMapperBuilder;
	}
}
