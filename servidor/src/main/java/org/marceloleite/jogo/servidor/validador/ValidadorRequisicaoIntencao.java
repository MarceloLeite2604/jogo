package org.marceloleite.jogo.servidor.validador;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.EmpresaBO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.TipoEmpresa;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoIntencao;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRequisicaoIntencao {

	@Inject
	private EmpresaBO empresaBO;

	public void validar(RequisicaoIntencao requisicaoIntencao) {
		Empresa empresa = empresaBO.obterPorIdOuLancarExcecao(requisicaoIntencao.getIdEmpresa());

		if (empresa.getTipo() != TipoEmpresa.JOGADOR) {
			throw new JogoRegraNegocioException(
					"A empresa " + requisicaoIntencao.getIdEmpresa() + " não é um jogador.");
		}
	}
}
