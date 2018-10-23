package org.marceloleite.jogo.iface.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.ehcache.Cache;
import org.marceloleite.jogo.iface.dao.ProdutoDAO;
import org.marceloleite.jogo.iface.modelo.Produto;
import org.marceloleite.jogo.iface.utils.CacheUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ProdutoBO {

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private Cache<Long, Produto> cacheProduto;

	@PostConstruct
	public void postConstruct() {
		obterTodos();
	}

	public Produto obter(long id) {
		return obterTodos().stream()
				.filter(produto -> produto.getId() == id)
				.findFirst()
				.orElse(null);
	}

	public List<Produto> obterTodos() {
		List<Produto> produtos = CacheUtils.obterTodos(cacheProduto);
		if (CollectionUtils.isEmpty(produtos)) {
			produtos = produtoDAO.obterTodos();
			inserirNaCache(produtos);
		}
		return produtos;
	}

	private void inserirNaCache(List<Produto> produtos) {
		produtos.forEach(produto -> cacheProduto.put(produto.getId(), produto));
	}
}
