package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.gerador.id.GeradorId;
import org.marceloleite.jogo.servidor.gerador.id.GeradorIdSequencial;
import org.marceloleite.jogo.servidor.serializer.IdSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Empresa implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	private static final GeradorId<Long> GERADOR_ID = new GeradorIdSequencial();

	private Empresa(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.nome = builder.nome;
		this.tipo = builder.tipo;
		this.caixa = builder.caixa;
		this.estoque = builder.estoque;
		this.ofertas = builder.ofertas;
		this.demandas = builder.demandas;
	}

	@NotNull
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private TipoEmpresa tipo;

	@NotNull
	@Min(0)
	private BigDecimal caixa;

	@NotNull
	@JsonSerialize(keyUsing = IdSerializer.class)
	private Map<Produto, BigDecimal> estoque;

	@NotNull
	private List<Intencao> ofertas;

	@NotNull
	private List<Intencao> demandas;

	@Override
	public Long getId() {
		return id;
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

	public Map<Produto, BigDecimal> getEstoque() {
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
		private String nome;
		private TipoEmpresa tipo;
		private BigDecimal caixa;
		private Map<Produto, BigDecimal> estoque = Collections.emptyMap();
		private List<Intencao> ofertas = Collections.emptyList();
		private List<Intencao> demandas = Collections.emptyList();

		private Builder() {
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

		public Builder estoque(Map<Produto, BigDecimal> estoque) {
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
