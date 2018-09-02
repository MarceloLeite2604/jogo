package org.marceloleite.jogo.servidor.configuracao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.modelo.Produto;

public class Configuracao {

	@NotNull
	@Min(0)
	private Double caixaInicialEmpresa;
	
	@NotNull
	private List<String> fornecedores;
	
	@NotNull
	private List<String> clientes;
	
	@NotNull
	private Map<Produto, BigDecimal> estoqueInicial;
	
	private Configuracao(Builder builder) {
		this.caixaInicialEmpresa = builder.caixaInicialEmpresa;
		this.fornecedores = builder.fornecedores;
		this.clientes = builder.clientes;
		this.estoqueInicial = builder.estoqueInicial;
	}
	
	public Double getCaixaInicialEmpresa() {
		return caixaInicialEmpresa;
	}
	
	public List<String> getFornecedores() {
		return fornecedores;
	}

	public List<String> getClientes() {
		return clientes;
	}
	
	public Map<Produto, BigDecimal> getEstoqueInicial() {
		return estoqueInicial;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Double caixaInicialEmpresa;
		private List<String> fornecedores = Collections.emptyList();
		private List<String> clientes = Collections.emptyList();
		private Map<Produto, BigDecimal> estoqueInicial = Collections.emptyMap();

		private Builder() {
		}

		public Builder caixaInicialEmpresa(Double caixaInicialEmpresa) {
			this.caixaInicialEmpresa = caixaInicialEmpresa;
			return this;
		}

		public Builder fornecedores(List<String> fornecedores) {
			this.fornecedores = fornecedores;
			return this;
		}

		public Builder clientes(List<String> clientes) {
			this.clientes = clientes;
			return this;
		}
		
		public Builder estoqueInicial(Map<Produto, BigDecimal> estoqueInicial) {
			this.estoqueInicial = estoqueInicial;
			return this;
		}

		public Configuracao build() {
			return new Configuracao(this);
		}
	}
}
