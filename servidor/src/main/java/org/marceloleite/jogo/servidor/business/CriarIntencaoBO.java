package org.marceloleite.jogo.servidor.business;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.gerador.GeradorIntencao;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoIntencao;
import org.marceloleite.jogo.servidor.validador.ValidadorIntencao;
import org.marceloleite.jogo.servidor.validador.ValidadorRequisicaoIntencao;
import org.springframework.stereotype.Component;

@Component
public class CriarIntencaoBO {

	@Inject
	private IntencaoBO intencaoBO;

	@Inject
	private EmpresaBO empresaBO;

	@Inject
	private CriarContratosBO criarContratosBO;

	@Inject
	private GeradorIntencao geradorIntencao;

	@Inject
	private ValidadorIntencao validadorIntencao;

	@Inject
	private ValidadorRequisicaoIntencao validadorRequisicaoIntencao;

	public Intencao criar(RequisicaoIntencao requisicaoIntencao) {
		Intencao intencao = converterRequisicao(requisicaoIntencao);
		Intencao intencaoSalva = salvarIntencao(intencao);
		atualizarModelo(intencaoSalva);
		return intencaoSalva;
	}

	private Intencao salvarIntencao(Intencao intencao) {
		validadorIntencao.validar(intencao);
		return intencaoBO.salvar(intencao);
	}

	private Intencao converterRequisicao(RequisicaoIntencao requisicaoIntencao) {
		validadorRequisicaoIntencao.validar(requisicaoIntencao);
		return geradorIntencao.gerar(requisicaoIntencao);
	}

	public void atualizarModelo(Intencao intencao) {
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			empresaBO.atualizarEstoque(intencao.getEmpresa(), intencao);
		} else {
			empresaBO.atualizarCaixa(intencao.getEmpresa(), intencao);
		}
		criarContratosBO.criar(intencao);
	}	
}
