package org.marceloleite.jogo.iface.util.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.client.utils.URIBuilder;
import org.marceloleite.jogo.iface.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.iface.excecao.JogoSistemaException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExecutorServicoRest {

	private RestTemplate restTemplate;

	public ExecutorServicoRest() {
		this.restTemplate = new RestTemplate();
	}

	public <E, S> S executarServico(SolicitacaoExecucaoServico<E, S> solicitacao) {
		URI uri = criarUri(solicitacao);

		HttpEntity<E> httpEntity = criarHttpEntity(solicitacao.getObjetoEnviar(), solicitacao.getMediaTypeEnviar(),
				solicitacao.getMediaTypesAceitos(), solicitacao.getValoresHeader());

		try {
			return restTemplate
					.exchange(uri, solicitacao.getHttpMethod(), httpEntity, solicitacao.getTipoObjetoReceber())
					.getBody();
		} catch (RestClientResponseException excecao) {
			if (excecao.getRawStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
				throw new JogoRegraNegocioException(excecao.getResponseBodyAsString());
			} else {
				throw new JogoSistemaException("Erro ao executar o serviço localizado no endereço \"" + uri + "\".",
						excecao);
			}
		}
	}

	private <E, S> URI criarUri(SolicitacaoExecucaoServico<E, S> solicitacao) {

		URI hostUri;
		try {
			hostUri = new URI(solicitacao.getHost());
		} catch (URISyntaxException excecao) {
			throw new JogoSistemaException(
					"Não foi possível compreender o endereço \"" + solicitacao.getHost() + "\".", excecao);
		}

		URIBuilder uriBuilder = new URIBuilder(hostUri);
		uriBuilder.setPath(solicitacao.getCaminhoServico());

		Optional.ofNullable(solicitacao.getParametros())
				.ifPresent(uriBuilder::setParameters);

		try {
			return uriBuilder.build();
		} catch (URISyntaxException excecao) {
			throw new JogoSistemaException("Erro ao elaborar o endereço do serviço.", excecao);
		}
	}

	private <T> HttpEntity<T> criarHttpEntity(T objeto, MediaType mediaTypeEnviado, List<MediaType> mediaTypesAceitos,
			Map<String, String> valoresHeader) {
		return new HttpEntity<>(objeto, criarHttpHeader(mediaTypeEnviado, mediaTypesAceitos, valoresHeader));
	}

	private HttpHeaders criarHttpHeader(MediaType mediaType, List<MediaType> mediaTypesAceitos,
			Map<String, String> valoresHeader) {
		HttpHeaders httpHeaders = new HttpHeaders();
		Optional.ofNullable(mediaType)
				.ifPresent(httpHeaders::setContentType);
		Optional.ofNullable(mediaTypesAceitos)
				.ifPresent(httpHeaders::setAccept);
		Optional.ofNullable(valoresHeader)
				.ifPresent(httpHeaders::setAll);
		return httpHeaders;
	}
}