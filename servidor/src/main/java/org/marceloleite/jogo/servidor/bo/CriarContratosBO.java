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

	public void criar(Intencao intencao) {
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			criarContratosOferta(intencao);
		} else {
			criarContratosDemanda(intencao);
		}
	}
	
	private void criarContratosOferta(Intencao oferta) {
		List<Intencao> demandasCobertas = intencaoBO.obterIntencoesAbertasCobertas(oferta);
		for (Intencao demandaCoberta : demandasCobertas) {
			Contrato contrato = criarContrato(oferta, demandaCoberta, oferta.getTipo());
			contratoBO.salvar(contrato);
			oferta.getContratosOferta()
					.add(contrato);
			intencaoBO.verificarPreenchimento(demandaCoberta);
		}
	}

	private void criarContratosDemanda(Intencao demanda) {
		List<Intencao> ofertasCobertas = intencaoBO.obterIntencoesAbertasCobertas(demanda);
		for (Intencao ofertaCoberta : ofertasCobertas) {
			Contrato contrato = criarContrato(ofertaCoberta, demanda, demanda.getTipo());
			contratoBO.salvar(contrato);
			demanda.getContratosDemanda()
					.add(contrato);
		}
	}
	
	private Contrato criarContrato(Intencao oferta, Intencao demanda, TipoIntencao tipoGerador) {
		return Contrato.builder()
				.oferta(oferta)
				.demanda(demanda)
				.precoUnitario(calcularPrecoUnitario(oferta, demanda, tipoGerador))
				.quantidade(calcularQuantidade(oferta, demanda))
				.itencaoGeradora(tipoGerador)
				.build();
	}

	private BigDecimal calcularPrecoUnitario(Intencao oferta, Intencao demanda, TipoIntencao tipoGerador) {
		if (TipoIntencao.DEMANDA.equals(tipoGerador)) {
			return demanda.getPrecoUnitario();
		} else {
			return oferta.getPrecoUnitario();
		}
	}

	private BigDecimal calcularQuantidade(Intencao oferta, Intencao demanda) {
		if (demanda.getQuantidade()
				.compareTo(oferta.getQuantidade()) > 0) {
			return demanda.getQuantidade();
		} else {
			return oferta.getQuantidade();
		}
	}
}
