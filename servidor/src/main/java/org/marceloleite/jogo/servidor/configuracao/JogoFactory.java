package org.marceloleite.jogo.servidor.configuracao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.configuracao.propriedades.PropriedadesJogo;
import org.marceloleite.jogo.servidor.configuracao.propriedades.PropriedadesProdutos;
import org.marceloleite.jogo.servidor.gerador.GeradorEstoqueInicial;
import org.marceloleite.libs.crypt.Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JogoFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(JogoFactory.class);
	
	@Inject
	private GeradorEstoqueInicial geradorEstoqueInicial;

	@Bean
	public Configuracao criarConfiguracao(PropriedadesJogo propriedadesJogo) {
		LOGGER.debug("Criando a configuração do jogo.");
		
		return Configuracao.builder()
				.caixaInicialEmpresa(propriedadesJogo.getCaixaInicialEmpresa())
				.clientes(propriedadesJogo.getClientes())
				.fornecedores(propriedadesJogo.getFornecedores())
				.estoqueInicial(criarEstoqueInicial(propriedadesJogo.getPropriedadesProdutos()))
				.build();
	}

	private Map<String, BigDecimal> criarEstoqueInicial(PropriedadesProdutos propriedadesProdutos) {
		return geradorEstoqueInicial.gerar(propriedadesProdutos);
	}
	
	@Bean
	public Crypt createEncryptorDecryptor() {
		return Crypt.builder()
				.keyEnvironmentVariableName(Optional.of("JOGO_ENCRYPT_KEY"))
				.cryptographicAlgorythm("DESede")
				.feedbackMode("CBC")
				.paddingScheme("PKCS5Padding")
				.build();
	}
}
