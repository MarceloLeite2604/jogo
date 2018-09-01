package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final GeradorID<UUID> GERADOR_ID = new GeradorUUID();
	
	@NotNull
	private UUID id;

	@NotNull
	private String nome;
	
	@NotNull
	private TipoEmpresa tipo;
	
	@NotNull
	@Min(0)
	private BigDecimal caixa;

	@NotNull
	private Map<Produto, Long> estoque;
	
	@NotNull
	private List<Intencao> ofertas;
	
	@NotNull
	private List<Intencao> demandas;

	private Empresa(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.nome = builder.nome;
		this.tipo = builder.tipo;
		this.caixa = builder.caixa;
		this.estoque = builder.estoque;
		this.ofertas = builder.ofertas;
		this.demandas = builder.demandas;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String nome;
		private TipoEmpresa tipo;
		private BigDecimal caixa;
		private Map<Produto, Long> estoque = Collections.emptyMap();
		private List<Intencao> ofertas = Collections.emptyList();
		private List<Intencao> demandas = Collections.emptyList();

		private Builder() {
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder tipo(TipoEmpresa tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder caixa(BigDecimal caixa) {
			this.caixa = caixa;
			return this;
		}

		public Builder estoque(Map<Produto, Long> estoque) {
			this.estoque = estoque;
			return this;
		}

		public Builder ofertas(List<Intencao> ofertas) {
			this.ofertas = ofertas;
			return this;
		}

		public Builder demandas(List<Intencao> demandas) {
			this.demandas = demandas;
			return this;
		}

		public Empresa build() {
			return new Empresa(this);
		}
	}
	
	
}
