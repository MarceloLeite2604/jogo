package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemEstoqueId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "empr_id", nullable = false)
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "prod_id", nullable = false)
	private Produto produto;
	
	private ItemEstoqueId() {
		// Construtor padrão para deserialização de objetos desta clase.
	}

	private ItemEstoqueId(Builder builder) {
		this.empresa = builder.empresa;
		this.produto = builder.produto;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Produto getProduto() {
		return produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		ItemEstoqueId other = (ItemEstoqueId) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemEstoqueId [empresa=" + empresa + ", produto=" + produto + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Empresa empresa;
		private Produto produto;

		private Builder() {
		}

		public Builder empresa(Empresa empresa) {
			this.empresa = empresa;
			return this;
		}

		public Builder produto(Produto produto) {
			this.produto = produto;
			return this;
		}

		public ItemEstoqueId build() {
			return new ItemEstoqueId(this);
		}
	}
	
}
