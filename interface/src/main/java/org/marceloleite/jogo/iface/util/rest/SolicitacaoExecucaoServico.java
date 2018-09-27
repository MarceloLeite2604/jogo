package org.marceloleite.jogo.iface.util.rest;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class SolicitacaoExecucaoServico<E, R> {

	private String host;

	private String caminhoServico;

	private List<NameValuePair> parametros;

	private E objetoEnviar;

	private MediaType mediaTypeEnviar;

	private List<MediaType> mediaTypesAceitos;

	private HttpMethod httpMethod;

	private ParameterizedTypeReference<E> tipoObjetoEnviar;

	private ParameterizedTypeReference<R> tipoObjetoReceber;

	private Map<String, String> valoresHeader;

	private SolicitacaoExecucaoServico(Builder<E, R> builder) {
		this.host = builder.host;
		this.caminhoServico = builder.caminhoServico;
		this.parametros = builder.parametros;
		this.objetoEnviar = builder.objetoEnviar;
		this.mediaTypeEnviar = builder.mediaTypeEnviar;
		this.mediaTypesAceitos = builder.mediaTypesAceitos;
		this.httpMethod = builder.httpMethod;
		this.tipoObjetoReceber = builder.tipoObjetoReceber;
		this.tipoObjetoEnviar = builder.tipoObjetoEnviar;
		this.valoresHeader = builder.valoresHeader;
	}

	public String getHost() {
		return host;
	}

	public String getCaminhoServico() {
		return caminhoServico;
	}

	public List<NameValuePair> getParametros() {
		return parametros;
	}

	public E getObjetoEnviar() {
		return objetoEnviar;
	}

	public MediaType getMediaTypeEnviar() {
		return mediaTypeEnviar;
	}

	public List<MediaType> getMediaTypesAceitos() {
		return mediaTypesAceitos;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public ParameterizedTypeReference<R> getTipoObjetoReceber() {
		return tipoObjetoReceber;
	}

	public ParameterizedTypeReference<E> getTipoObjetoEnvio() {
		return tipoObjetoEnviar;
	}

	public Map<String, String> getValoresHeader() {
		return valoresHeader;
	}

	public static <E, R> Builder<E, R> builder(ParameterizedTypeReference<E> tipoObjetoEnvio,
			ParameterizedTypeReference<R> tipoObjetoRetorno) {
		return new Builder<>(tipoObjetoEnvio, tipoObjetoRetorno);
	}

	public static final class Builder<E, R> {

		private String host;

		private String caminhoServico;

		private List<NameValuePair> parametros = new LinkedList<>();

		private E objetoEnviar;

		private MediaType mediaTypeEnviar = MediaType.TEXT_PLAIN;

		private List<MediaType> mediaTypesAceitos = Arrays.asList(MediaType.APPLICATION_JSON);

		private HttpMethod httpMethod;

		private ParameterizedTypeReference<E> tipoObjetoEnviar;

		private ParameterizedTypeReference<R> tipoObjetoReceber;

		private Map<String, String> valoresHeader = new HashMap<>();

		public Builder(ParameterizedTypeReference<E> tipoObjetoEnvio,
				ParameterizedTypeReference<R> tipoObjetoRetorno) {
			this.tipoObjetoEnviar = tipoObjetoEnvio;
			this.tipoObjetoReceber = tipoObjetoRetorno;
		}

		public Builder<E, R> host(String host) {
			this.host = host;
			return this;
		}

		public Builder<E, R> caminhoServico(String caminhoServico) {
			this.caminhoServico = caminhoServico;
			return this;
		}

		public Builder<E, R> valoresHeader(Map<String, String> valoresHeader) {
			this.valoresHeader = valoresHeader;
			return this;
		}

		public Builder<E, R> parametros(List<NameValuePair> parametros) {
			this.parametros = parametros;
			return this;
		}

		public Builder<E, R> parametros(NameValuePair... parametros) {
			this.parametros = Arrays.asList(parametros);
			return this;
		}

		public Builder<E, R> objetoEnviar(E objetoEnviar) {
			this.objetoEnviar = objetoEnviar;
			return this;
		}

		public Builder<E, R> mediaTypeEnviar(MediaType mediaTypeEnviado) {
			this.mediaTypeEnviar = mediaTypeEnviado;
			return this;
		}

		public Builder<E, R> mediaTypesAceitos(List<MediaType> mediaTypesAceitos) {
			this.mediaTypesAceitos = mediaTypesAceitos;
			return this;
		}

		public Builder<E, R> mediaTypesAceitos(MediaType mediaTypesAceitos) {
			this.mediaTypesAceitos = Arrays.asList(mediaTypesAceitos);
			return this;
		}

		public Builder<E, R> httpMethod(HttpMethod httpMethod) {
			this.httpMethod = httpMethod;
			return this;
		}

		public SolicitacaoExecucaoServico<E, R> build() {
			return new SolicitacaoExecucaoServico<>(this);
		}
	}

}
