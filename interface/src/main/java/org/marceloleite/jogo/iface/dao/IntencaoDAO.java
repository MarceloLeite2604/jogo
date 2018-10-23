package org.marceloleite.jogo.iface.dao;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.configuracao.propriedades.JogoProperties;
import org.marceloleite.jogo.iface.modelo.Intencao;
import org.marceloleite.jogo.iface.utils.rest.ExecutorServicoRest;
import org.marceloleite.jogo.iface.utils.rest.SolicitacaoExecucaoServico;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class IntencaoDAO {

	private static final String CAMINHO_SERVICO = "/intencao";
	
	@Inject
	private JogoProperties jogoProperties;

	@Inject
	private ExecutorServicoRest executorServicoRest;

	public List<Intencao> obterTodas() {
		SolicitacaoExecucaoServico<Void, List<Intencao>> solicitacaoExecucaoServico = SolicitacaoExecucaoServico
				.builder(new ParameterizedTypeReference<Void>() {
				}, new ParameterizedTypeReference<List<Intencao>>() {
				})
				.host(jogoProperties.getEnderecoServidor())
				.caminhoServico(CAMINHO_SERVICO)
				.httpMethod(HttpMethod.GET)
				.mediaTypesAceitos(MediaType.APPLICATION_JSON)
				.build();

		return executorServicoRest.executarServico(solicitacaoExecucaoServico);
	}
}
