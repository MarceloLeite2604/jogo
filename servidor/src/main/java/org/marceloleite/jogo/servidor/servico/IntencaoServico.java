package org.marceloleite.jogo.servidor.servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.ContratoBO;
import org.marceloleite.jogo.servidor.bo.EmpresaBO;
import org.marceloleite.jogo.servidor.bo.IntencaoBO;
import org.marceloleite.jogo.servidor.excecao.ValidacaoException;
import org.marceloleite.jogo.servidor.modelo.Contrato;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.marceloleite.jogo.servidor.validador.ValidadorIntencao;

public class IntencaoServico {

	@Inject
	private IntencaoBO intencaoBO;

	@Inject
	private EmpresaBO empresaBO;

	@Inject
	private ContratoBO contratoBO;

	@Inject
	private ValidadorIntencao validadorIntencao;

	public Intencao criar(Intencao intencao) throws ValidacaoException {
		validar(intencao);
		Intencao intencaoSalva = intencaoBO.salvar(intencao);
		atualizarModelo(intencaoSalva);
		return intencaoSalva;
	}

	private void validar(Intencao intencao) throws ValidacaoException {
		validadorIntencao.validar(intencao);
	}

	public void atualizarModelo(Intencao intencao) {
		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			empresaBO.atualizarEstoque(intencao.getEmpresa(), intencao);
			gerarContratosOferta(intencao);
		} else {
			empresaBO.atualizarCaixa(intencao.getEmpresa(), intencao);
			gerarContratosDemanda(intencao);
		}
	}

	private void gerarContratosOferta(Intencao oferta) {
		List<Intencao> demandasCobertas = obterIntencoesAbertasCobertas(oferta);
		for (Intencao demandaCoberta : demandasCobertas) {
			Contrato contrato = criarContrato(oferta, demandaCoberta, oferta.getTipo());
			contratoBO.salvar(contrato);
			oferta.getContratos()
					.add(contrato);
			// TODO Atualizar quantidades nas intencoes.
		}
	}

	private void gerarContratosDemanda(Intencao demanda) {
		List<Intencao> ofertasCobertas = obterIntencoesAbertasCobertas(demanda);
		for (Intencao ofertaCoberta : ofertasCobertas) {
			Contrato contrato = criarContrato(ofertaCoberta, demanda, demanda.getTipo());
			contratoBO.salvar(contrato);
			demanda.getContratos()
					.add(contrato);
			// TODO Atualizar quantidades nas intencoes.
		}
	}

	private Contrato criarContrato(Intencao oferta, Intencao demanda, TipoIntencao tipoGerador) {
		Contrato contrato = Contrato.builder()
				.oferta(oferta)
				.demanda(demanda)
				.precoUnitario(calcularPrecoUnitario(oferta, demanda, tipoGerador))
				.quantidade(calcularQuantidade(oferta, demanda))
				.itencaoGeradora(TipoIntencao.DEMANDA)
				.build();
		return contrato;
	}

	private BigDecimal calcularPrecoUnitario(Intencao oferta, Intencao demanda, TipoIntencao tipoGerador) {
		if (TipoIntencao.DEMANDA.equals(tipoGerador)) {
			return demanda.getPrecoUnitarioAtual();
		} else {
			return oferta.getPrecoUnitarioAtual();
		}
	}

	private BigDecimal calcularQuantidade(Intencao oferta, Intencao demandaCoberta) {
		if (demandaCoberta.getQuantidadeAtual()
				.compareTo(oferta.getQuantidadeAtual()) > 0) {
			return demandaCoberta.getQuantidadeAtual();
		} else {
			return oferta.getQuantidadeAtual();
		}
	}

	private List<Intencao> obterIntencoesAbertasCobertas(Intencao intencao) {
		return intencaoBO.obterContratosAbertos(calcularTipoProcurado(intencao))
				.stream()
				.filter(intencaoObtida -> intencao.getPrecoUnitarioAtual()
						.compareTo(intencaoObtida.getPrecoUnitarioAtual()) >= 0)
				.filter(demanda -> !intencao.getEmpresa()
						.equals(demanda.getEmpresa()))
				.collect(Collectors.toList());
	}

	private TipoIntencao calcularTipoProcurado(Intencao intencao) {
		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			return TipoIntencao.DEMANDA;
		} else {
			return TipoIntencao.OFERTA;
		}
	}

	public List<Intencao> obter(Long id, TipoIntencao tipo) {
		if (id != null) {
			return obterPorId(id);
		} else if (tipo != null) {
			return obterPorTipo(tipo);
		} else {
			return obterTodos();
		}
	}

	private ArrayList<Intencao> obterTodos() {
		return new ArrayList<>(intencaoBO.obterTodos());
	}

	private List<Intencao> obterPorId(Long id) {
		return Arrays.asList((intencaoBO.obterPorId(id)
				.orElse(null)));
	}

	private List<Intencao> obterPorTipo(TipoIntencao tipo) {
		return intencaoBO.obterPorTipo(tipo);
	}
}
