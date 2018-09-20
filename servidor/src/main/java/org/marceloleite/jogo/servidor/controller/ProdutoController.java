package org.marceloleite.jogo.servidor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.ProdutoBO;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Inject
	private ProdutoBO produtoBO;

	@GetMapping
	public Object get(@RequestParam(value = "id",
			required = false) Long id) {
		if (id != null) {
			return produtoBO.obterPorIdOuLancarExcecao(id);
		} else {
			return produtoBO.obterPorPartida();
		}
	}

	private ArrayList<Produto> obterTodos() {
		Iterable<Produto> iterableProdutos = produtoBO.obterTodos();
		ArrayList<Produto> produtos = new ArrayList<>();
		iterableProdutos.forEach(produtos::add);
		return produtos;
	}

	private List<Produto> obterPorId(Long id) {
		return Arrays.asList((produtoBO.obterPorId(id)
				.orElse(null)));
	}
}
