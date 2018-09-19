package org.marceloleite.jogo.servidor.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.marceloleite.jogo.servidor.bo.EmpresaBO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoEmpresa;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Inject
	private EmpresaBO empresaBO;

	@GetMapping
	public Object get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return empresaBO.obterPorId(id)
					.orElseThrow(() -> new JogoRegraNegocioException(
							"Não foi possível encontrar a empresa de código " + id + "."));
		} else {
			return empresaBO.obterTodos();
		}
	}

	@DeleteMapping
	public String delete(@RequestParam Long id) {
		if (!empresaBO.excluir(id)) {
			throw new JogoRegraNegocioException("Não existe uma empresa de código " + id + " para excluir.");
		}
		return "Empresa de código " + id + "excluída.";
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Empresa post(@Valid @RequestBody RequisicaoEmpresa requisicaoEmpresa) {
		return empresaBO.criar(requisicaoEmpresa);
	}
}
