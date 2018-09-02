package org.marceloleite.jogo.servidor.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDAO implements BaseDAO<Produto, Long> {

	private Map<Long, Produto> produtos = new HashMap<>();

	@Override
	public Produto salvar(Produto produto) {
		produtos.put(produto.getId(), produto);
		return produto;
	}

	@Override
	public Optional<Produto> obterPorId(Long id) {
		return Optional.ofNullable(produtos.get(id));
	}

	@Override
	public Collection<Produto> obterTodos() {
		return produtos.values();
	}

	@Override
	public boolean excluir(Long id) {
		return Optional.ofNullable(produtos.remove(id))
				.isPresent();

	}
}
