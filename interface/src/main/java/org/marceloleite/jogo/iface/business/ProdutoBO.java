package org.marceloleite.jogo.iface.business;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.ehcache.Cache;
import org.marceloleite.jogo.iface.dao.ProdutoDAO;
import org.marceloleite.jogo.iface.modelo.FormaBusca;
import org.marceloleite.jogo.iface.modelo.Produto;
import org.springframework.stereotype.Component;

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
		return obter(id, FormaBusca.COMPLETA);
	}

	public Produto obter(long id, FormaBusca formaBusca) {
		Optional<Produto> optionalProduto = Optional.ofNullable(obterDaCache(id));
		if (!optionalProduto.isPresent() && FormaBusca.COMPLETA.equals(formaBusca)) {
			optionalProduto = Optional.ofNullable(obterDoServidor(id));
		}
		return optionalProduto.orElse(null);
	}

	public List<Produto> obterTodos() {
		return obterDoServidor();
	}

	private Produto obterDaCache(long id) {
		Produto produto = null;
		if (cacheProduto.containsKey(id)) {
			produto = cacheProduto.get(id);
		}
		return produto;
	}

	private List<Produto> obterDoServidor() {
		List<Produto> produtos = produtoDAO.obterTodos();
		inserirNaCache(produtos);
		return produtos;
	}

	private Produto obterDoServidor(long id) {
		return obterDoServidor().stream()
				.filter(produto -> produto.getId() == id)
				.findFirst()
				.orElse(null);
	}

	private void inserirNaCache(List<Produto> produtos) {
		produtos.forEach(produto -> cacheProduto.put(produto.getId(), produto));
	}
}
