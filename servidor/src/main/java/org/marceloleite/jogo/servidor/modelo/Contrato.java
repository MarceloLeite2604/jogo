package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.gerador.id.GeradorId;
import org.marceloleite.jogo.servidor.gerador.id.GeradorIdSequencial;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contratos")
@SequenceGenerator(name = "cont",
		sequenceName = "cont",
		allocationSize = 1)
public class Contrato implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	private static final GeradorId<Long> GERADOR_ID = new GeradorIdSequencial();

	@Id
	@Column(name = "id",
			nullable = false)
	@NotNull
	@Min(1)
	private Long id;

	@JsonIgnore
	@JoinColumn(name = "inte_id_oferta",
			nullable = false)
	@NotNull
	@ManyToOne
	private Intencao oferta;

	@JsonIgnore
	@JoinColumn(name = "inte_id_demanda",
			nullable = false)
	@NotNull
	@ManyToOne
	private Intencao demanda;

	@Column(name = "quantidade",
			nullable = false)
	@NotNull
	private BigDecimal quantidade;

	@Column(name = "preco_unitario",
			nullable = false)
	@NotNull
	private BigDecimal precoUnitario;

	@Column(name = "tipo_intencao_geradora",
			nullable = false)
	@NotNull
	private TipoIntencao itencaoGeradora;

	@Override
	public Long getId() {
		return id;
	}

	public Intencao getOferta() {
		return oferta;
	}

	public Intencao getDemanda() {
		return demanda;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public TipoIntencao getItencaoGeradora() {
		return itencaoGeradora;
	}

	private Contrato(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.oferta = builder.oferta;
		this.demanda = builder.demanda;
		this.quantidade = builder.quantidade;
		this.precoUnitario = builder.precoUnitario;
		this.itencaoGeradora = builder.itencaoGeradora;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Intencao oferta;
		private Intencao demanda;
		private BigDecimal quantidade;
		private BigDecimal precoUnitario;
		private TipoIntencao itencaoGeradora;

		private Builder() {
		}

		public Builder oferta(Intencao oferta) {
			this.oferta = oferta;
			return this;
		}

		public Builder demanda(Intencao demanda) {
			this.demanda = demanda;
			return this;
		}

		public Builder quantidade(BigDecimal quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public Builder precoUnitario(BigDecimal precoUnitario) {
			this.precoUnitario = precoUnitario;
			return this;
		}

		public Builder itencaoGeradora(TipoIntencao itencaoGeradora) {
			this.itencaoGeradora = itencaoGeradora;
			return this;
		}

		public Contrato build() {
			return new Contrato(this);
		}
	}

}
