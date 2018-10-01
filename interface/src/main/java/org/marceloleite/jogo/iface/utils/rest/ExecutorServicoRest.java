package org.marceloleite.jogo.iface.utils.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.http.client.utils.URIBuilder;
import org.marceloleite.jogo.iface.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.iface.excecao.JogoSistemaException;
import org.marceloleite.jogo.iface.modelo.erroweb.ErroServicoWeb;
import org.marceloleite.jogo.iface.utils.ErroServicoWebUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExecutorServicoRest {

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private ObjectMapper objectMapper;

	public <E, S> S executarServico(SolicitacaoExecucaoServico<E, S> solicitacao) {
		URI uri = criarUri(solicitacao);

		HttpEntity<E> httpEntity = criarHttpEntity(solicitacao.getObjetoEnviar(), solicitacao.getMediaTypeEnviar(),
				solicitacao.getMediaTypesAceitos(), solicitacao.getValoresHeader());

		try {
			return restTemplate
					.exchange(uri, solicitacao.getHttpMethod(), httpEntity, solicitacao.getTipoObjetoReceber())
					.getBody();
		} catch (RestClientResponseException excecaoRestClientResponse) {
			if (excecaoRestClientResponse.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()) {
				throw new JogoSistemaException(elaborarMensagemErro400(uri, excecaoRestClientResponse));
			} else if (excecaoRestClientResponse.getRawStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
				throw new JogoRegraNegocioException(elaborarMensagemErro422(uri, excecaoRestClientResponse));
			} else {
				throw new JogoSistemaException(escreverCabecalhoMensagemErro(uri), excecaoRestClientResponse);
			}
		}
	}

	private String escreverCabecalhoMensagemErro(URI uri) {
		return "Erro ao executar o serviço localizado no endereço \"" + uri + "\".";
	}

	private String elaborarMensagemErro400(URI uri, RestClientResponseException excecaoRestClientResponse) {
		try {
			ErroServicoWeb erroServicoWeb = objectMapper.readValue(excecaoRestClientResponse.getResponseBodyAsString(),
					ErroServicoWeb.class);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(escreverCabecalhoMensagemErro(uri) + "\n");
			stringBuilder.append(ErroServicoWebUtils.formatarMensagem(erroServicoWeb));
			return stringBuilder.toString();
		} catch (IOException exception) {
			return escreverCabecalhoMensagemErro(uri) + "\nMensagem retornada: "
					+ excecaoRestClientResponse.getResponseBodyAsString();
		}
	}

	private String elaborarMensagemErro422(URI uri, RestClientResponseException excecaoRestClientResponse) {
		try {
			ErroServicoWeb erroServicoWeb = objectMapper.readValue(excecaoRestClientResponse.getResponseBodyAsString(),
					ErroServicoWeb.class);
			return erroServicoWeb.getMessage();
		} catch (IOException exception) {
			return escreverCabecalhoMensagemErro(uri) + "\nMensagem retornada: "
					+ excecaoRestClientResponse.getResponseBodyAsString();
		}
	}

	private <E, S> URI criarUri(SolicitacaoExecucaoServico<E, S> solicitacao) {

		URI hostUri;
		try {
			hostUri = new URI(solicitacao.getHost());
		} catch (URISyntaxException excecao) {
			throw new JogoSistemaException("Não foi possível compreender o endereço \"" + solicitacao.getHost() + "\".",
					excecao);
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