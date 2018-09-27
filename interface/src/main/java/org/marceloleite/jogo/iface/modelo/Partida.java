package org.marceloleite.jogo.iface.modelo;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.marceloleite.libraries.time.deserializer.ZonedDateTimeDeserializer;
import org.marceloleite.libraries.time.serializer.ZonedDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Partida implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Partida() {
		// Construtor padrão para deserialização de objetos desta classe.
	}

	private Long id;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonDeserialize(using = ZonedDateTimeDeserializer.class)
	private ZonedDateTime instante;

	private Partida(Builder builder) {
		this.instante = builder.instante;
	}

	public Long getId() {
		return id;
	}

	public ZonedDateTime getInstante() {
		return instante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Partida other = (Partida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", instante=" + instante + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private ZonedDateTime instante;

		private Builder() {
		}

		public Builder instante(ZonedDateTime instante) {
			this.instante = instante;
			return this;
		}

		public Partida build() {
			return new Partida(this);
		}
	}
}
