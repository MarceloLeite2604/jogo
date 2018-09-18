package org.marceloleite.jogo.servidor.gerador;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.marceloleite.jogo.servidor.configuracao.propriedades.PropriedadesProdutos;
import org.springframework.stereotype.Component;

@Component
public class GeradorEstoqueInicial {

	public Map<String, BigDecimal> gerar(PropriedadesProdutos propriedadesProdutos) {

		Map<String, BigDecimal> estoqueInicial = new HashMap<>(propriedadesProdutos.getNome()
				.size());

		for (int contador = 0; contador < propriedadesProdutos.getNome()
				.size(); contador++) {
			String nome = propriedadesProdutos.getNome()
					.get(contador);
			BigDecimal quantidade = BigDecimal.valueOf(propriedadesProdutos.getQuantidadeInicial()
					.get(contador));
			estoqueInicial.put(nome, quantidade);
		}
		return estoqueInicial;
	}
}
