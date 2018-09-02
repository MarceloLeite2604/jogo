package org.marceloleite.jogo.servidor.modelo.requisicao;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class RequisicaoEmpresa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O nome da empresa não pode ser nulo.")
	private String nome;

	private RequisicaoEmpresa(Builder builder) {
		this.nome = builder.nome;
	}
	
	private RequisicaoEmpresa() {
		// Construtor padrão para deserialização de objetos desta classe.
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String nome;

		private Builder() {
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public RequisicaoEmpresa build() {
			return new RequisicaoEmpresa(this);
		}
	}
}
