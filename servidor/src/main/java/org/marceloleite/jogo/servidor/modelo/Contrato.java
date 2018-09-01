package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Contrato implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final GeradorID<Long> GERADOR_ID = new GeradorIDSequencial();
	
	@NotNull
	@Min(1)
	private long id;
	
	@NotNull
	private Intencao oferta;
	
	@NotNull
	private Intencao demanda;
	
	@NotNull
	private TipoIntencao itencaoGeradora;

	private Contrato(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.oferta = builder.oferta;
		this.demanda = builder.demanda;
		this.itencaoGeradora = builder.itencaoGeradora;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Intencao oferta;
		private Intencao demanda;
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

		public Builder itencaoGeradora(TipoIntencao itencaoGeradora) {
			this.itencaoGeradora = itencaoGeradora;
			return this;
		}

		public Contrato build() {
			return new Contrato(this);
		}
	}
	
	
}
