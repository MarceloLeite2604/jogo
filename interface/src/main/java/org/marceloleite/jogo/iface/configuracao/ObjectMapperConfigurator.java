package org.marceloleite.jogo.iface.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ObjectMapperConfigurator {

	@Bean
	public ObjectMapper criarObjectMapper() {
		return new ObjectMapper();
	}

}
