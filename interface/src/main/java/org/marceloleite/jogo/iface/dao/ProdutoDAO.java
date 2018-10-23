package org.marceloleite.jogo.iface.dao;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.configuracao.propriedades.JogoProperties;
import org.marceloleite.jogo.iface.modelo.Produto;
import org.marceloleite.jogo.iface.utils.rest.ExecutorServicoRest;
import org.marceloleite.jogo.iface.utils.rest.SolicitacaoExecucaoServico;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDAO {

	private static final String CAMINHO_SERVICO = "/produto";

	@Inject
	private JogoProperties jogoProperties;

	@Inject
	private ExecutorServicoRest executorServicoRest;
	
	public List<Produto> obterTodos() {
		SolicitacaoExecucaoServico<Void, List<Produto>> solicitacaoExecucaoServico = SolicitacaoExecucaoServico
				.builder(new ParameterizedTypeReference<Void>() {
				}, new ParameterizedTypeReference<List<Produto>>() {
				})
				.host(jogoProperties.getEnderecoServidor())
				.caminhoServico(CAMINHO_SERVICO)
				.httpMethod(HttpMethod.GET)
				.mediaTypeEnviar(MediaType.APPLICATION_JSON)
				.mediaTypesAceitos(MediaType.APPLICATION_JSON)
				.build();
		
		return executorServicoRest.executarServico(solicitacaoExecucaoServico);
	}
}
