package org.marceloleite.jogo.servidor.configuracao;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.mapper.EstoqueInicialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JogoFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(JogoFactory.class);
	
	@Inject
	private EstoqueInicialMapper estoqueInicialMapper;
	
	@Bean
	public Configuracao criarConfiguracao(ApplicationProperties applicationProperties) {
		LOGGER.debug("Criando a configuração do jogo.");
		return Configuracao.builder()
				.caixaInicialEmpresa(applicationProperties.getCaixaInicialEmpresa())
				.clientes(applicationProperties.getClientes())
				.fornecedores(applicationProperties.getFornecedores())
				.estoqueInicial(estoqueInicialMapper.map(applicationProperties.getEstoqueInicial()))
				.build();
	}
}
