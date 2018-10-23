package org.marceloleite.jogo.iface.configuracao;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
			Jackson2ObjectMapperBuilder objectMapperBuilder) {
		return new MappingJackson2HttpMessageConverter(objectMapperBuilder.build());
	}

	@Bean
	public RestTemplate criarRestTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		
		for (HttpMessageConverter<?> httpMessageConverter : messageConverters) {
			if ( httpMessageConverter instanceof MappingJackson2HttpMessageConverter ) {
				messageConverters.remove(httpMessageConverter);
				messageConverters.add(mappingJackson2HttpMessageConverter);
			}
		}
		
		return restTemplate;
	}
}
