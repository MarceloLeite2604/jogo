package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final GeradorID<Long> GERADOR_ID = new GeradorIDSequencial();

	@NotNull
	@Min(1)
	private long id;

	@NotNull
	private String nome;

	private Produto(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.nome = builder.nome;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String nome;

		private Builder() {
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Produto build() {
			return new Produto(this);
		}
	}
}
