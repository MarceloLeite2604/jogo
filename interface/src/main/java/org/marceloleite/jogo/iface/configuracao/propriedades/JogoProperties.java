package org.marceloleite.jogo.iface.configuracao.propriedades;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Valid
@Configuration
@PropertySource("/WEB-INF/application.properties")
public class JogoProperties {

	@NotNull
	@Value("${jogo.enderecoServidor}")
	private String enderecoServidor;

	public String getEnderecoServidor() {
		return enderecoServidor;
	}

	public void setEnderecoServidor(String enderecoServidor) {
		this.enderecoServidor = enderecoServidor;
	}
}
