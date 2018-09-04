package org.marceloleite.jogo.servidor.modelo;

import java.time.Duration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.marceloleite.jogo.servidor.gerador.id.GeradorId;
import org.marceloleite.jogo.servidor.gerador.id.GeradorIdSequencial;

public class Periodo {
	
	private static final GeradorId<Long> GERADOR_ID = new GeradorIdSequencial();
	
	@NotNull
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private TipoPeriodo tipo;

	@NotNull
	private Duration duracao;
	
	@NotNull
	@Min(0)
	@Max(1)
	private Double fatorDemanda;
	
	@NotNull
	@Min(0)
	@Max(1)
	private Double fatorOferta;
	
	@NotNull
	@Min(1)
	private Integer ciclos;
	
	@NotNull
	private Duration duracaoCiclos;

	private Periodo(Builder builder) {
		this.id = GERADOR_ID.gerar();
		this.nome = builder.nome;
		this.tipo = builder.tipo;
		this.duracao = builder.duracao;
		this.fatorDemanda = builder.fatorDemanda;
		this.fatorOferta = builder.fatorOferta;
		this.ciclos = builder.ciclos;
		this.duracaoCiclos = builder.duracaoCiclos;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String nome;
		private TipoPeriodo tipo;
		private Duration duracao;
		private Double fatorDemanda;
		private Double fatorOferta;
		private Integer ciclos;
		private Duration duracaoCiclos;

		private Builder() {
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder tipo(TipoPeriodo tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder duracao(Duration duracao) {
			this.duracao = duracao;
			return this;
		}

		public Builder fatorDemanda(Double fatorDemanda) {
			this.fatorDemanda = fatorDemanda;
			return this;
		}

		public Builder fatorOferta(Double fatorOferta) {
			this.fatorOferta = fatorOferta;
			return this;
		}

		public Builder ciclos(Integer ciclos) {
			this.ciclos = ciclos;
			return this;
		}

		public Builder duracaoCiclos(Duration duracaoCiclos) {
			this.duracaoCiclos = duracaoCiclos;
			return this;
		}

		public Periodo build() {
			return new Periodo(this);
		}
	}
	
	
}
