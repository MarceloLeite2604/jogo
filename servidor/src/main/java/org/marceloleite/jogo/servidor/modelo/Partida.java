package org.marceloleite.jogo.servidor.modelo;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

@Entity
@Table(name = "partidas")
@SequenceGenerator(name = "part",
		sequenceName = "part",
		allocationSize = 1)
public class Partida implements Entidade<Long> {

	private static final long serialVersionUID = 1L;
	
	protected Partida() {
		// Construtor padrão para deserialização de objetos desta classe.
	}

	@Id
	@GeneratedValue(generator = "part")
	private Long id;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@Column(name = "instante",
			nullable = false)
	private ZonedDateTime instante;

	private Partida(Builder builder) {
		this.instante = builder.instante;
	}

	@Override
	public Long getId() {
		return id;
	}

	public ZonedDateTime getInstante() {
		return instante;
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
