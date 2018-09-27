package org.marceloleite.jogo.servidor.business;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.modelo.Contrato;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class CriarContratosBO {

	@Inject
	private IntencaoBO intencaoBO;

	@Inject
	private ContratoBO contratoBO;

	public void criar(Intencao intencaoGerada) {
		List<Intencao> intencoesCobertas = intencaoBO.obterIntencoesAbertasCobertas(intencaoGerada);
		for (Intencao intencaoCoberta : intencoesCobertas) {
			Contrato contrato = criarContrato(intencaoGerada, intencaoCoberta);
			adicionarContrato(intencaoGerada, contrato);
			intencaoCoberta.getContratosDemanda()
					.add(contrato);
			intencaoBO.verificarPreenchimento(intencaoCoberta);
			contratoBO.salvar(contrato);
		}
	}

	private void adicionarContrato(Intencao intencao, Contrato contrato) {
		intencao.getContratos()
				.add(contrato);
		intencaoBO.verificarPreenchimento(intencao);
	}

	private Contrato criarContrato(Intencao geradora, Intencao coberta) {
		return Contrato.builder()
				.oferta(geradora.getTipo() == TipoIntencao.OFERTA ? geradora : coberta)
				.demanda(geradora.getTipo() == TipoIntencao.DEMANDA ? geradora : coberta)
				.precoUnitario(coberta.getPrecoUnitario())
				.quantidade(calcularQuantidade(geradora, coberta))
				.intencaoGeradora(geradora.getTipo())
				.build();
	}

	private BigDecimal calcularQuantidade(Intencao geradora, Intencao coberta) {
		if (geradora.getQuantidade()
				.compareTo(coberta.getQuantidade()) > 0) {
			return geradora.getQuantidade();
		} else {
			return coberta.getQuantidade();
		}
	}
}
