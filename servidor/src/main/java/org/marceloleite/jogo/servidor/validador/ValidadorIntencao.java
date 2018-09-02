package org.marceloleite.jogo.servidor.validador;

import java.math.BigDecimal;

import org.marceloleite.jogo.servidor.excecao.ValidacaoException;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class ValidadorIntencao {

	public void validar(Intencao intencao) {
		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			validarOferta(intencao);
		} else {
			validarDemanda(intencao);
		}
	}

	private void validarOferta(Intencao intencao) {
		BigDecimal quantidadeEstoque = intencao.getEmpresa()
				.getEstoque()
				.get(intencao.getProduto());
		if (intencao.getQuantidadeAtual()
				.compareTo(quantidadeEstoque) > 0) {
			throw new ValidacaoException("A quantidade informada na intenção excede a quantidade em estoque.");
		}
	}

	private void validarDemanda(Intencao intencao) {
		BigDecimal custo = intencao.getPrecoTotalAtual();
		if (custo.compareTo(intencao.getEmpresa()
				.getCaixa()) > 0) {
			throw new ValidacaoException("O custo para esta demanda excede o caixa da empresa.");
		}
	}
}
