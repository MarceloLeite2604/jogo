package org.marceloleite.jogo.iface.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Partida partida;

	private String nome;

	private Produto() {
		// Construtor padrão para deserialização de objetos.
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Partida getPartida() {
		return partida;
	}

	public String getNome() {
		return nome;
	}

	private Produto(Builder builder) {
		this.id = builder.id;
		this.partida = builder.partida;
		this.nome = builder.nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private Partida partida;
		private String nome;

		private Builder() {
		}
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder partida(Partida partida) {
			this.partida = partida;
			return this;
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
