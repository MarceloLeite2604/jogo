package org.marceloleite.jogo.iface.dao;

import javax.inject.Inject;

import org.apache.http.message.BasicNameValuePair;
import org.marceloleite.jogo.iface.configuracao.propriedades.JogoProperties;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.modelo.requisicao.RequisicaoEmpresa;
import org.marceloleite.jogo.iface.utils.rest.ExecutorServicoRest;
import org.marceloleite.jogo.iface.utils.rest.SolicitacaoExecucaoServico;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class EmpresaDAO {

	private static final String CAMINHO_SERVICO = "/empresa";

	private static final String PARAMETRO_NOME = "nome";

	private static final String PARAMETRO_ID = "id";

	@Inject
	private JogoProperties jogoProperties;

	@Inject
	private ExecutorServicoRest executorServicoRest;

	public Empresa criar(String nome) {
		RequisicaoEmpresa requisicaoEmpresa = criarRequisicao(nome);

		SolicitacaoExecucaoServico<RequisicaoEmpresa, Empresa> solicitacaoExecucaoServico = SolicitacaoExecucaoServico
				.builder(new ParameterizedTypeReference<RequisicaoEmpresa>() {
				}, new ParameterizedTypeReference<Empresa>() {
				})
				.host(jogoProperties.getEnderecoServidor())
				.caminhoServico(CAMINHO_SERVICO)
				.httpMethod(HttpMethod.POST)
				.mediaTypeEnviar(MediaType.APPLICATION_JSON)
				.mediaTypesAceitos(MediaType.APPLICATION_JSON)
				.objetoEnviar(requisicaoEmpresa)
				.build();

		return executorServicoRest.executarServico(solicitacaoExecucaoServico);
	}

	private RequisicaoEmpresa criarRequisicao(String nome) {
		return RequisicaoEmpresa.builder()
				.nome(nome)
				.build();
	}
}
