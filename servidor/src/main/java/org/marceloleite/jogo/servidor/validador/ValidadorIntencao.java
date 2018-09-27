package org.marceloleite.jogo.servidor.validador;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.business.ItemEstoqueBO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class ValidadorIntencao {

	@Inject
	private ItemEstoqueBO itemEstoqueBO;

	public void validar(Intencao intencao) {

		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			validarOferta(intencao);
		} else {
			validarDemanda(intencao);
		}
	}

	private void validarOferta(Intencao intencao) {
		ItemEstoque itemEstoque = itemEstoqueBO.obter(intencao.getEmpresa(), intencao.getProduto());
		if (intencao.getQuantidade()
				.compareTo(itemEstoque.getQuantidade()) > 0) {
			throw new JogoRegraNegocioException("A quantidade informada na intenção excede a quantidade em estoque.");
		}
	}

	private void validarDemanda(Intencao intencao) {
		BigDecimal custo = intencao.getPrecoTotalAtual();
		if (custo.compareTo(intencao.getEmpresa()
				.getCaixa()) > 0) {
			throw new JogoRegraNegocioException("O custo para esta demanda excede o caixa da empresa.");
		}
	}
}
