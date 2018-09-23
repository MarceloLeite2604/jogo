package org.marceloleite.jogo.servidor.bo;

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

	public void criar(Intencao oferta) {
		List<Intencao> demandasCobertas = intencaoBO.obterIntencoesAbertasCobertas(oferta);
		for (Intencao demandaCoberta : demandasCobertas) {
			Contrato contrato = criarContrato(oferta, demandaCoberta);
			contratoBO.salvar(contrato);
			oferta.getContratosOferta()
					.add(contrato);
			intencaoBO.verificarPreenchimento(demandaCoberta);
		}
	}

	private Contrato criarContrato(Intencao geradora, Intencao coberta) {
		return Contrato.builder()
				.oferta(geradora.getTipo() == TipoIntencao.OFERTA ? geradora : coberta)
				.demanda(geradora.getTipo() == TipoIntencao.DEMANDA ? geradora : coberta)
				.precoUnitario(coberta.getPrecoUnitario())
				.quantidade(calcularQuantidade(geradora, coberta))
				.itencaoGeradora(geradora.getTipo())
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
