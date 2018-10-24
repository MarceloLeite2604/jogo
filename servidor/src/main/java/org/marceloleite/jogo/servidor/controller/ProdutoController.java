package org.marceloleite.jogo.servidor.controller;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.business.ProdutoBO;
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
}
