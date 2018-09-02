package org.marceloleite.jogo.servidor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.marceloleite.jogo.servidor.excecao.ValidacaoException;
import org.marceloleite.jogo.servidor.gerador.GeradorIntencao;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoIntencao;
import org.marceloleite.jogo.servidor.servico.IntencaoServico;
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
	private IntencaoServico intencaoServico;

	@Inject
	private GeradorIntencao geradorIntencao;

	@GetMapping
	public List<Intencao> get(@RequestParam(required = false) Long id,
			@RequestParam(required = false) TipoIntencao tipo) {
		return intencaoServico.obter(id, tipo);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Intencao post(@Valid @RequestBody RequisicaoIntencao requisicaoIntencao) throws ValidacaoException {
		return intencaoServico.criar(geradorIntencao.gerar(requisicaoIntencao));
	}
}
