package org.marceloleite.jogo.servidor.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.marceloleite.jogo.servidor.bo.CriarIntencaoBO;
import org.marceloleite.jogo.servidor.bo.IntencaoBO;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoIntencao;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intencao")
public class IntencaoController {

	@Inject
	private CriarIntencaoBO criarIntencaoBO;
	
	@Inject
	private IntencaoBO intencaoBO;

	@GetMapping
	public Object get(@RequestParam(required = false) Long id) {
		if ( id != null ) {
			return intencaoBO.obterPorIdOuLancarExcecao(id);
		}
		
		return intencaoBO.obterTodos();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Intencao post(@Valid @RequestBody RequisicaoIntencao requisicaoIntencao) {
		return criarIntencaoBO.criar(requisicaoIntencao);
	}
}
