package org.marceloleite.jogo.iface.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Contrato() {
		// Construtor padrão para deserialização de objetos.
	}

	private Long id;

	private Intencao oferta;

	private Intencao demanda;

	private BigDecimal quantidade;

	private BigDecimal precoUnitario;

	private TipoIntencao intencaoGeradora;

	public Long getId() {
		return id;
	}

	public Intencao getOferta() {
		return oferta;
	}

	public Intencao getDemanda() {
		return demanda;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public TipoIntencao getIntencaoGeradora() {
		return intencaoGeradora;
	}

	private Contrato(Builder builder) {
		this.oferta = builder.oferta;
		this.demanda = builder.demanda;
		this.quantidade = builder.quantidade;
		this.precoUnitario = builder.precoUnitario;
		this.intencaoGeradora = builder.intencaoGeradora;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Intencao oferta;
		private Intencao demanda;
		private BigDecimal quantidade;
		private BigDecimal precoUnitario;
		private TipoIntencao intencaoGeradora;

		private Builder() {
		}

		public Builder oferta(Intencao oferta) {
			this.oferta = oferta;
			return this;
		}

		public Builder demanda(Intencao demanda) {
			this.demanda = demanda;
			return this;
		}

		public Builder quantidade(BigDecimal quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public Builder precoUnitario(BigDecimal precoUnitario) {
			this.precoUnitario = precoUnitario;
			return this;
		}

		public Builder intencaoGeradora(TipoIntencao intencaoGeradora) {
			this.intencaoGeradora = intencaoGeradora;
			return this;
		}

		public Contrato build() {
			return new Contrato(this);
		}
	}

}
