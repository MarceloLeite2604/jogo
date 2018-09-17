package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_estoque")
public class ItemEstoque implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ItemEstoque() {
		// Construtor padrão para deserialização de objetos desta classe.
	}

	@Id
	@ManyToOne
	@JoinColumn(name="empr_id")
	private Empresa empresa;

	@Id
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Produto produto;

	@Column(name = "quantidade",
			nullable = false)
	private BigDecimal quantidade;

	private ItemEstoque(Builder builder) {
		this.empresa = builder.empresa;
		this.produto = builder.produto;
		this.quantidade = builder.quantidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Empresa empresa;
		private Produto produto;
		private BigDecimal quantidade;

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

		public Builder quantidade(BigDecimal quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public ItemEstoque build() {
			return new ItemEstoque(this);
		}
	}

}
