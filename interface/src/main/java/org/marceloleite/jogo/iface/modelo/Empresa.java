package org.marceloleite.jogo.iface.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Empresa() {
		// Construtor padrão para deserialização de objetos.
	}

	private Empresa(Builder builder) {
		this.id = builder.id;
		this.partida = builder.partida;
		this.nome = builder.nome;
		this.tipo = builder.tipo;
		this.caixa = builder.caixa;
		this.estoque = builder.estoque;
		this.ofertas = builder.ofertas;
		this.demandas = builder.demandas;
	}

	private Long id;

	private Partida partida;

	private String nome;

	private TipoEmpresa tipo;

	private BigDecimal caixa;

	private List<ItemEstoque> estoque;

	private List<Intencao> ofertas;

	private List<Intencao> demandas;

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

	public TipoEmpresa getTipo() {
		return tipo;
	}

	public BigDecimal getCaixa() {
		return caixa;
	}

	public void setCaixa(BigDecimal caixa) {
		this.caixa = caixa;
	}

	public List<ItemEstoque> getEstoque() {
		return estoque;
	}

	public List<Intencao> getOfertas() {
		return ofertas;
	}

	public List<Intencao> getDemandas() {
		return demandas;
	}

	public static Builder builder() {
		return new Builder();
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + "]";
	}

	public static final class Builder {
		private Long id;
		private Partida partida;
		private String nome;
		private TipoEmpresa tipo;
		private BigDecimal caixa;
		private List<ItemEstoque> estoque = Collections.emptyList();
		private List<Intencao> ofertas = Collections.emptyList();
		private List<Intencao> demandas = Collections.emptyList();

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

		public Builder tipo(TipoEmpresa tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder caixa(BigDecimal caixa) {
			this.caixa = caixa;
			return this;
		}

		public Builder estoque(List<ItemEstoque> estoque) {
			this.estoque = estoque;
			return this;
		}

		public Builder ofertas(List<Intencao> ofertas) {
			this.ofertas = ofertas;
			return this;
		}

		public Builder demandas(List<Intencao> demandas) {
			this.demandas = demandas;
			return this;
		}

		public Empresa build() {
			return new Empresa(this);
		}
	}
}
