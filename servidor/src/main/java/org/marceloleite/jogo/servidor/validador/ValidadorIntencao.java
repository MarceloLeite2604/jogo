package org.marceloleite.jogo.servidor.validador;

import java.math.BigDecimal;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.ItemEstoqueBO;
import org.marceloleite.jogo.servidor.excecao.ValidacaoException;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class ValidadorIntencao {

	@Inject
	private ItemEstoqueBO itemEstoqueBO;

	public void validar(Intencao intencao) throws ValidacaoException {
		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			validarOferta(intencao);
		} else {
			validarDemanda(intencao);
		}
	}

	private void validarOferta(Intencao intencao) throws ValidacaoException {
		Optional<ItemEstoque> optionalItemEstoque = itemEstoqueBO.obter(intencao.getEmpresa(), intencao.getProduto());
		if (!optionalItemEstoque.isPresent() || intencao.getQuantidade()
				.compareTo(optionalItemEstoque.get()
						.getQuantidade()) > 0) {
			throw new ValidacaoException("A quantidade informada na intenção excede a quantidade em estoque.");
		}
	}

	private void validarDemanda(Intencao intencao) throws ValidacaoException {
		BigDecimal custo = intencao.getPrecoTotalAtual();
		if (custo.compareTo(intencao.getEmpresa()
				.getCaixa()) > 0) {
			throw new ValidacaoException("O custo para esta demanda excede o caixa da empresa.");
		}
	}
}
