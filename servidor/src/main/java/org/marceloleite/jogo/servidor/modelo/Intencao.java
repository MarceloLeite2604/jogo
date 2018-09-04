package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.gerador.id.GeradorId;
import org.marceloleite.jogo.servidor.gerador.id.GeradorIdSequencial;
import org.marceloleite.jogo.servidor.serializer.IdCampoSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Intencao implements Entidade<Long> {

	private static final long serialVersionUID = 1L;

	private static final GeradorId<Long> GERADOR_ID = new GeradorIdSequencial();

	@NotNull
	@Min(1)
	private Long id;

	@NotNull
	@JsonSerialize(using = IdCampoSerializer.class)
	private Empresa empresa;

	@NotNull
	private TipoIntencao tipo;

	@NotNull
	private StatusIntencao status;

	@NotNull
	@JsonSerialize(using = IdCampoSerializer.class)
	private Produto produto;

	@NotNull
	@Min(0)
	private BigDecimal precoUnitario;

	@NotNull
	@Min(1)
	private BigDecimal quantidade;

	private List<Contrato> contratos;

	private Intencao() {
		// Construtor padrão para deserialização de objetos.
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

	public List<Contrato> getContratos() {
		return contratos;
	}

	@JsonIgnore
	public BigDecimal getPrecoTotalAtual() {
		return precoUnitario.multiply(quantidade);
	}

	private Intencao(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.empresa = builder.empresa;
		this.tipo = builder.tipo;
		this.status = builder.status;
		this.produto = builder.produto;
		this.precoUnitario = builder.precoUnitario;
		this.quantidade = builder.quantidade;
		this.contratos = builder.contratos;
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
		private List<Contrato> contratos = Collections.emptyList();

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

		public Builder contrato(List<Contrato> contratos) {
			this.contratos = contratos;
			return this;
		}

		public Intencao build() {
			return new Intencao(this);
		}
	}

}
