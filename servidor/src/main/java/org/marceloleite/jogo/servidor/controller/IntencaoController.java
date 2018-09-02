package org.marceloleite.jogo.servidor.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.marceloleite.jogo.servidor.atualizador.AtualizadorModeloIntencao;
import org.marceloleite.jogo.servidor.bo.IntencaoBO;
import org.marceloleite.jogo.servidor.gerador.GeradorIntencao;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoIntencao;
import org.marceloleite.jogo.servidor.validador.ValidadorIntencao;
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
	private IntencaoBO intencaoBO;

	@Inject
	private ValidadorIntencao validadorIntencao;

	@Inject
	private GeradorIntencao geradorIntencao;

	@Inject
	private AtualizadorModeloIntencao atualizadorModeloIntencao;

	@GetMapping
	public Intencao get(@RequestParam(value = "id") Long id) {
		return intencaoBO.obterPorId(id)
				.orElse(null);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Intencao post(@Valid @RequestBody RequisicaoIntencao requisicaoIntencao) {
		Intencao intencao = geradorIntencao.gerar(requisicaoIntencao);
		validar(intencao);
		Intencao intencaoSalva = intencaoBO.salvar(geradorIntencao.gerar(requisicaoIntencao));
		atualizarModelo(intencao);
		return intencaoSalva;
	}

	private void atualizarModelo(Intencao intencao) {
		atualizadorModeloIntencao.atualizarModeloParaCriacao(intencao);
	}

	private void validar(Intencao intencao) {
		validadorIntencao.validar(intencao);
	}
}
