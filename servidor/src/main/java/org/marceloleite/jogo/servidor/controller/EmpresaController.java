package org.marceloleite.jogo.servidor.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.EmpresaBO;
import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.marceloleite.jogo.servidor.modelo.TipoEmpresa;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Inject
	private EmpresaBO empresaBO;

	@Inject
	private Configuracao configuracao;

	@GetMapping
	public Empresa get(@RequestParam(value = "id") UUID id) {
		return empresaBO.obter(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Empresa post(@RequestParam("nome") String nome) {

		Empresa empresa = Empresa.builder()
				.caixa(BigDecimal.valueOf(configuracao.getCaixaInicialEmpresa()))
				.estoque(criarEstoqueInicial())
				.nome(nome)
				.tipo(TipoEmpresa.JOGADOR)
				.build();

		return empresaBO.salvar(empresa);
	}

	private Map<Produto, Long> criarEstoqueInicial() {
		return new HashMap<>(configuracao.getEstoqueInicial());
	}
}
