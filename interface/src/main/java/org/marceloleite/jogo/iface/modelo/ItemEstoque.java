package org.marceloleite.jogo.iface.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemEstoque implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ItemEstoque() {
		// Construtor padrão para deserialização de objetos desta classe.
	}

	private ItemEstoqueId id;

	private BigDecimal quantidade;

	private ItemEstoque(Builder builder) {
		this.id = builder.id;
		this.quantidade = builder.quantidade;
	}

	public ItemEstoqueId getId() {
		return id;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		ItemEstoque other = (ItemEstoque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

	public static final class Builder {
		private ItemEstoqueId id;
		private BigDecimal quantidade;

		private Builder() {
		}

		public Builder id(ItemEstoqueId id) {
			this.id = id;
			return this;
		}

		public Builder quantidade(BigDecimal quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public ItemEstoque build() {
			return new ItemEstoque(this);
		}
	}

}
