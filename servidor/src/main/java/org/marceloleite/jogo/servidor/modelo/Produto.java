package org.marceloleite.jogo.servidor.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produtos")
@SequenceGenerator(name = "prod",
		sequenceName = "prod",
		allocationSize = 1)
public class Produto implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "prod")
	@Column(name = "id",
			nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name="part_id")
	private Partida partida;

	@NotNull
	@Column(name = "nome",
			nullable = false)
	private String nome;

	private Produto() {
		// Construtor padrão para deserialização de objetos.
	}

	@Override
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
		private Partida partida;
		private String nome;

		private Builder() {
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
