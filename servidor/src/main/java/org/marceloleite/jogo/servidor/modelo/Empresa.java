package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "empresas")
@SequenceGenerator(name = "empr",
		sequenceName = "empr",
		allocationSize = 1)
public class Empresa implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	protected Empresa() {
		// Construtor padrão para deserialização de objetos.
	}

	private Empresa(Builder builder) {
		this.partida = builder.partida;
		this.nome = builder.nome;
		this.tipo = builder.tipo;
		this.caixa = builder.caixa;
		this.estoque = builder.estoque;
		this.ofertas = builder.ofertas;
		this.demandas = builder.demandas;
	}

	@Id
	@GeneratedValue(generator = "empr")
	@Column(name = "id",
			nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "part_id")
	private Partida partida;

	@Column(name = "nome",
			nullable = false)
	@NotNull
	private String nome;

	@Column(name = "tipo",
			nullable = false)
	@NotNull
	private TipoEmpresa tipo;

	@Column(name = "caixa",
			nullable = false)
	@NotNull
	@Min(0)
	private BigDecimal caixa;

	@NotNull
	@OneToMany(mappedBy = "id.empresa",
			cascade = CascadeType.ALL)
	private List<ItemEstoque> estoque;

	@NotNull
	@OneToMany(mappedBy = "empresa")
	@Where(clause = "tipo = 0")
	private List<Intencao> ofertas;

	@NotNull
	@OneToMany(mappedBy = "empresa")
	@Where(clause = "tipo = 1")
	private List<Intencao> demandas;

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
		private Partida partida;
		private String nome;
		private TipoEmpresa tipo;
		private BigDecimal caixa;
		private List<ItemEstoque> estoque = Collections.emptyList();
		private List<Intencao> ofertas = Collections.emptyList();
		private List<Intencao> demandas = Collections.emptyList();

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
