package org.marceloleite.jogo.servidor.modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Tempo {

	@NotNull
	private Long ordem;

	@NotNull
	private Long duracao;

	@NotNull
	private List<Tempo> divisoes;

	private Tempo(Builder builder) {
		this.ordem = builder.ordem;
		this.duracao = builder.duracao;
		this.divisoes = builder.divisoes;
	}

	public Long getDuracao() {
		return duracao;
	}

	public List<Long> obterNiveis(long instante) {

		if (instante > duracao) {
			return Collections.emptyList();
		}

		List<Long> niveis = new LinkedList<>();
		niveis.add(ordem);

		long ultimoTempo = 0L;
		for (Tempo divisao : divisoes) {
			if (instante >= ultimoTempo && instante < divisao.getDuracao()) {
				niveis.addAll(divisao.obterNiveis(instante - ultimoTempo));
			}
		}
		return niveis;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long ordem;
		private Long duracao;
		private List<Tempo> divisoes = Collections.emptyList();

		private Builder() {
		}

		public Builder ordem(Long ordem) {
			this.ordem = ordem;
			return this;
		}

		public Builder duracao(Long duracao) {
			this.duracao = duracao;
			return this;
		}

		public Builder divisoes(List<Tempo> divisoes) {
			this.divisoes = divisoes;
			return this;
		}

		public Tempo build() {
			return new Tempo(this);
		}
	}

}
