package org.marceloleite.jogo.servidor.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produtos")
@SequenceGenerator(name = "prod",
		sequenceName = "prod")
public class Produto implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", nullable = false)
	@Min(1)
	private Long id;

	@NotNull
	@Column(name="nome", nullable = false)
	private String nome;
	
	private Produto() {
		// Construtor padrão para deserialização de objetos.
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	private Produto(Builder builder) {
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
