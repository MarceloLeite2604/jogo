package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Intencao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final GeradorID<Long> GERADOR_ID = new GeradorIDSequencial();

	@NotNull
	@Min(1)
	private long id;

	@NotNull
	private TipoIntencao tipo;

	@NotNull
	private Produto produto;

	@NotNull
	@Min(0)
	private BigDecimal precoUnitarioOriginal;

	@NotNull
	@Min(1)
	private int quantidadeOriginal;

	@NotNull
	@Min(0)
	private int quantidadeAtual;

	private Intencao(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.tipo = builder.tipo;
		this.produto = builder.produto;
		this.precoUnitarioOriginal = builder.precoUnitarioOriginal;
		this.quantidadeOriginal = builder.quantidadeOriginal;
		this.quantidadeAtual = builder.quantidadeAtual;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private TipoIntencao tipo;
		private Produto produto;
		private BigDecimal precoUnitarioOriginal;
		private int quantidadeOriginal;
		private int quantidadeAtual;

		private Builder() {
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

		public Builder quantidadeOriginal(int quantidadeOriginal) {
			this.quantidadeOriginal = quantidadeOriginal;
			return this;
		}

		public Builder quantidadeAtual(int quantidadeAtual) {
			this.quantidadeAtual = quantidadeAtual;
			return this;
		}

		public Intencao build() {
			return new Intencao(this);
		}
	}

}
