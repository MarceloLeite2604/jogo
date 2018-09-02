package org.marceloleite.jogo.servidor.configuracao;

import java.util.Map;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.gerador.GeradorEstoqueInicial;
import org.marceloleite.jogo.servidor.gerador.GeradorProduto;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JogoFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(JogoFactory.class);

	@Inject
	private GeradorEstoqueInicial geradorEstoqueInicial;
	
	@Inject
	private GeradorProduto geradorProduto;

	@Bean
	public Configuracao criarConfiguracao(ApplicationProperties applicationProperties) {
		LOGGER.debug("Criando a configuração do jogo.");
		
		gerarProdutos(applicationProperties);
		
		return Configuracao.builder()
				.caixaInicialEmpresa(applicationProperties.getCaixaInicialEmpresa())
				.clientes(applicationProperties.getClientes())
				.fornecedores(applicationProperties.getFornecedores())
				.estoqueInicial(criarEstoqueInicial(applicationProperties))
				.build();
	}

	private void gerarProdutos(ApplicationProperties applicationProperties) {
		LOGGER.debug("Criando produtos.");
		geradorProduto.gerar(applicationProperties.getProdutos());
	}

	private Map<Produto, Long> criarEstoqueInicial(ApplicationProperties applicationProperties) {
		return geradorEstoqueInicial.gerar(applicationProperties.getQuantidadesIniciaisEstoque());
	}
}
