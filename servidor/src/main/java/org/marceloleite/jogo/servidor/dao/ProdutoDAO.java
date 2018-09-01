package org.marceloleite.jogo.servidor.dao;

import java.util.HashMap;
import java.util.Map;

import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDAO {

	private Map<Long, Produto> produtos = new HashMap<>();
	
	public Produto salvar(Produto produto) {
		produtos.put(produto.getId(), produto);
		return produto;
	}
	
	public Produto obter(Long id) {
		return produtos.get(id);
	}
}
