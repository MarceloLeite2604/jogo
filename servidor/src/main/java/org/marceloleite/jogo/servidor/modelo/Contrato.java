package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Contrato implements Entidade<Long> {
	
	private static final long serialVersionUID = 1L;
	
	private static final GeradorID<Long> GERADOR_ID = new GeradorIDSequencial();
	
	@NotNull
	@Min(1)
	private Long id;
	
	@NotNull
	private Intencao oferta;
	
	@NotNull
	private Intencao demanda;
	
	@NotNull
	private BigDecimal quantidade;
	
	@NotNull
	private BigDecimal precoUnitario;
	
	@NotNull
	private TipoIntencao itencaoGeradora;

	@Override
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

	public TipoIntencao getItencaoGeradora() {
		return itencaoGeradora;
	}

	private Contrato(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.oferta = builder.oferta;
		this.demanda = builder.demanda;
		this.quantidade = builder.quantidade;
		this.precoUnitario = builder.precoUnitario;
		this.itencaoGeradora = builder.itencaoGeradora;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Intencao oferta;
		private Intencao demanda;
		private BigDecimal quantidade;
		private BigDecimal precoUnitario;
		private TipoIntencao itencaoGeradora;

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

		public Builder itencaoGeradora(TipoIntencao itencaoGeradora) {
			this.itencaoGeradora = itencaoGeradora;
			return this;
		}

		public Contrato build() {
			return new Contrato(this);
		}
	}
	
	
}
