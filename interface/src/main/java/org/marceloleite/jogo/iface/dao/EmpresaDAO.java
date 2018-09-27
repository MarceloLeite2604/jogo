package org.marceloleite.jogo.iface.dao;

import javax.inject.Inject;

import org.apache.http.message.BasicNameValuePair;
import org.marceloleite.jogo.iface.configuracao.propriedades.JogoProperties;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.util.rest.ExecutorServicoRest;
import org.marceloleite.jogo.iface.util.rest.SolicitacaoExecucaoServico;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class EmpresaDAO {

	private static final String CAMINHO_SERVICO = "/empresas";

	private static final String PARAMETRO_NOME = "nome";

	private static final String PARAMETRO_ID = "id";

	@Inject
	private JogoProperties jogoProperties;

	@Inject
	private ExecutorServicoRest executorServicoRest;

	public Empresa criar(String nome) {
		SolicitacaoExecucaoServico<Void, Empresa> solicitacaoExecucaoServico = SolicitacaoExecucaoServico
				.builder(new ParameterizedTypeReference<Void>() {
				}, new ParameterizedTypeReference<Empresa>() {
				})
				.host(jogoProperties.getEnderecoServidor())
				.httpMethod(HttpMethod.POST)
				.caminhoServico(CAMINHO_SERVICO)
				.parametros(new BasicNameValuePair(PARAMETRO_NOME, nome))
				.build();

		return executorServicoRest.executarServico(solicitacaoExecucaoServico);
	}
}
