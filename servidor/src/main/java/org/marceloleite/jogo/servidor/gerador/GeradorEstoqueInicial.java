package org.marceloleite.jogo.servidor.gerador;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.ProdutoBO;
import org.marceloleite.jogo.servidor.excecao.ServidorRuntimeException;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class GeradorEstoqueInicial {

	@Inject
	private ProdutoBO produtoBO;

	public Map<Produto, Long> gerar(Map<Long, Long> quantidadesIniciaisEstoque) {

		Map<Produto, Long> estoqueInicial = new HashMap<>(quantidadesIniciaisEstoque.keySet()
				.size());

		for (Entry<Long, Long> entryQuantidadeInicialEstoque : quantidadesIniciaisEstoque.entrySet()) {
			Long id = entryQuantidadeInicialEstoque.getKey();
			Long quantidade = entryQuantidadeInicialEstoque.getValue();
			
			Produto produto = produtoBO.obterPorId(entryQuantidadeInicialEstoque.getKey())
					.orElseThrow(() -> new ServidorRuntimeException(
							"Não foi possível localizar o produto de código " + id + "."));
			
			estoqueInicial.put(produto, quantidade);
		}

		return estoqueInicial;
	}
}
