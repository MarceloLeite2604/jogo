package org.marceloleite.jogo.servidor.modelo.predicado;

import java.math.BigDecimal;
import java.util.function.Predicate;

import org.marceloleite.jogo.servidor.modelo.Intencao;

public class PredicadoIntencaoMaiorPreco implements Predicate<Intencao> {
	
	private BigDecimal precoLimite;
	
	public PredicadoIntencaoMaiorPreco(BigDecimal precoLimite) {
		this.precoLimite = precoLimite;
	}

	@Override
	public boolean test(Intencao intencao) {
		return (intencao.getPrecoUnitario().compareTo(precoLimite) >= 0);
	}

}
