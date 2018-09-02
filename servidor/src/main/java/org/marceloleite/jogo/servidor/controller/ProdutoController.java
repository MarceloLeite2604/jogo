package org.marceloleite.jogo.servidor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	public List<Produto> get(@RequestParam(value = "id",
			required = false) Long id) {
		return Optional.ofNullable(id)
				.map(this::obterPorId)
				.orElse(obterTodos());
	}

	private ArrayList<Produto> obterTodos() {
		return new ArrayList<>(produtoBO.obterTodos());
	}

	private List<Produto> obterPorId(Long id) {
		return Arrays.asList((produtoBO.obterPorId(id)
				.orElse(null)));
	}
}
