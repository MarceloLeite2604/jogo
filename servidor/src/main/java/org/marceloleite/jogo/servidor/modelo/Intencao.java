package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.validacao.grupo.GrupoValidacaoBanco;

public class Intencao implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	private static final GeradorID<Long> GERADOR_ID = new GeradorIDSequencial();

	@NotNull(groups = { GrupoValidacaoBanco.class })
	@Min(1)
	private Long id;

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

	private Intencao() {
		// Construtor padrão para deserialização de objetos.
	}

	public Long getId() {
		return id;
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

	public int getQuantidadeOriginal() {
		return quantidadeOriginal;
	}

	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

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
