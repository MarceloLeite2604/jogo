package org.marceloleite.jogo.iface.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ItemEstoqueId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Empresa empresa;

	private Produto produto;

	private ItemEstoqueId() {
		// Construtor padrão para deserialização de objetos desta clase.
	}

	private ItemEstoqueId(Builder builder) {
		this.empresa = builder.empresa;
		this.produto = builder.produto;
	}

	@JsonIgnore
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
