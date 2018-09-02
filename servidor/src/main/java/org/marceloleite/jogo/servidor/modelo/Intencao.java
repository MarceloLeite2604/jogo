package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.serializer.IdCampoSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Intencao implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	private static final GeradorID<Long> GERADOR_ID = new GeradorIDSequencial();

	@NotNull
	@Min(1)
	private Long id;

	@NotNull
	@JsonSerialize(using = IdCampoSerializer.class)
	private Empresa empresa;

	@NotNull
	private TipoIntencao tipo;

	@NotNull
	@JsonSerialize(using = IdCampoSerializer.class)
	private Produto produto;

	@NotNull
	@Min(0)
	private BigDecimal precoUnitarioOriginal;

	@NotNull
	@Min(0)
	private BigDecimal precoUnitarioAtual;

	@NotNull
	@Min(1)
	private BigDecimal quantidadeOriginal;

	@NotNull
	@Min(0)
	private BigDecimal quantidadeAtual;

	private Intencao() {
		// Construtor padrão para deserialização de objetos.
	}

	public Long getId() {
		return id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public TipoIntencao getTipo() {
		return tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getPrecoUnitarioOriginal() {
		return precoUnitarioOriginal;
	}

	public BigDecimal getPrecoUnitarioAtual() {
		return precoUnitarioAtual;
	}

	public BigDecimal getQuantidadeOriginal() {
		return quantidadeOriginal;
	}

	public BigDecimal getQuantidadeAtual() {
		return quantidadeAtual;
	}

	@JsonIgnore
	public BigDecimal getPrecoTotalAtual() {
		return precoUnitarioAtual.multiply(quantidadeAtual);
	}

	private Intencao(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.empresa = builder.empresa;
		this.tipo = builder.tipo;
		this.produto = builder.produto;
		this.precoUnitarioOriginal = builder.precoUnitarioOriginal;
		this.precoUnitarioAtual = builder.precoUnitarioOriginal;
		this.quantidadeOriginal = builder.quantidadeOriginal;
		this.quantidadeAtual = builder.quantidadeOriginal;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private TipoIntencao tipo;
		private Empresa empresa;
		private Produto produto;
		private BigDecimal precoUnitarioOriginal;
		private BigDecimal quantidadeOriginal;

		private Builder() {
		}

		public Builder empresa(Empresa empresa) {
			this.empresa = empresa;
			return this;
		}

		public Builder tipo(TipoIntencao tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder produto(Produto produto) {
			this.produto = produto;
			return this;
		}

		public Builder precoUnitarioOriginal(BigDecimal precoUnitarioOriginal) {
			this.precoUnitarioOriginal = precoUnitarioOriginal;
			return this;
		}

		public Builder quantidadeOriginal(BigDecimal quantidadeOriginal) {
			this.quantidadeOriginal = quantidadeOriginal;
			return this;
		}

		public Intencao build() {
			return new Intencao(this);
		}
	}

}
