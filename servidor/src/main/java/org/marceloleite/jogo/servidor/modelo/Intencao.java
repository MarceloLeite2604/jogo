package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.serializer.IdCampoSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "intencoes")
@SequenceGenerator(name = "inte",
		sequenceName = "inte")
public class Intencao implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id",
			nullable = false)
	@Min(1)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="empr_id")
	@JsonSerialize(using = IdCampoSerializer.class)
	private Empresa empresa;

	@Column(name = "tipo",
			nullable = false)
	@NotNull
	private TipoIntencao tipo;

	@Column(name = "status",
			nullable = false)
	@NotNull
	private StatusIntencao status;

	@NotNull
	@ManyToOne
	@JoinColumn(name="prod_id")
	@JsonSerialize(using = IdCampoSerializer.class)
	private Produto produto;

	@Column(name = "preco_unitario",
			nullable = false)
	@NotNull
	@Min(0)
	private BigDecimal precoUnitario;

	@Column(name = "quantidade",
			nullable = false)
	@NotNull
	@Min(1)
	private BigDecimal quantidade;

	@OneToMany(mappedBy = "demanda")
	private List<Contrato> contratosDemanda;

	@OneToMany(mappedBy = "oferta")
	private List<Contrato> contratosOferta;

	private Intencao() {
		// Construtor padrão para deserialização de objetos.
	}

	private Intencao(Builder builder) {
		this.empresa = builder.empresa;
		this.tipo = builder.tipo;
		this.status = builder.status;
		this.produto = builder.produto;
		this.precoUnitario = builder.precoUnitario;
		this.quantidade = builder.quantidade;
		this.contratosOferta = builder.contratosOferta;
		this.contratosDemanda = builder.contratosDemanda;
	}

	public Long getId() {
		return id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public TipoIntencao getTipo() {
		return tipo;
	}

	public StatusIntencao getStatus() {
		return status;
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public List<Contrato> getContratosDemanda() {
		return contratosDemanda;
	}

	public List<Contrato> getContratosOferta() {
		return contratosOferta;
	}

	@JsonIgnore
	public BigDecimal getPrecoTotalAtual() {
		return precoUnitario.multiply(quantidade);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private TipoIntencao tipo;
		private StatusIntencao status;
		private Empresa empresa;
		private Produto produto;
		private BigDecimal precoUnitario;
		private BigDecimal quantidade;
		private List<Contrato> contratosOferta = Collections.emptyList();
		private List<Contrato> contratosDemanda = Collections.emptyList();

		private Builder() {
		}

		public Builder empresa(Empresa empresa) {
			this.empresa = empresa;
			return this;
		}

		public Builder tipo(TipoIntencao tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder tipo(StatusIntencao status) {
			this.status = status;
			return this;
		}

		public Builder produto(Produto produto) {
			this.produto = produto;
			return this;
		}

		public Builder precoUnitario(BigDecimal precoUnitario) {
			this.precoUnitario = precoUnitario;
			return this;
		}

		public Builder quantidade(BigDecimal quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public Builder contratosOferta(List<Contrato> contratosOferta) {
			this.contratosOferta = contratosOferta;
			return this;
		}

		public Builder contratosDemanda(List<Contrato> contratosDemanda) {
			this.contratosDemanda = contratosDemanda;
			return this;
		}

		public Intencao build() {
			return new Intencao(this);
		}
	}
}
