package org.marceloleite.jogo.servidor.gerador;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.business.EmpresaBO;
import org.marceloleite.jogo.servidor.business.ProdutoBO;
import org.marceloleite.jogo.servidor.excecao.ServidorRuntimeException;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.marceloleite.jogo.servidor.modelo.StatusIntencao;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoIntencao;
import org.springframework.stereotype.Component;

@Component
public class GeradorIntencao {

	@Inject
	private EmpresaBO empresaBO;

	@Inject
	private ProdutoBO produtoBO;

	public Intencao gerar(RequisicaoIntencao requisicaoIntencao) {
		Empresa empresa = obterEmpresa(requisicaoIntencao.getIdEmpresa());
		Produto produto = obterProduto(requisicaoIntencao.getIdProduto());

		return Intencao.builder()
				.empresa(empresa)
				.produto(produto)
				.tipo(requisicaoIntencao.getTipo())
				.status(StatusIntencao.ABERTO)
				.precoUnitario(BigDecimal.valueOf(requisicaoIntencao.getPrecoUnitario()))
				.quantidade(BigDecimal.valueOf(requisicaoIntencao.getQuantidade()))
				.contratosDemanda(new ArrayList<>())
				.contratosOferta(new ArrayList<>())
				.build();
	}

	private Produto obterProduto(Long id) {
		return produtoBO.obterPorId(id)
				.orElseThrow(() -> new ServidorRuntimeException(
						"Não foi possível localizar o produto de código " + id + "."));
	}

	private Empresa obterEmpresa(Long id) {
		return empresaBO.obterPorId(id)
				.orElseThrow(() -> new ServidorRuntimeException(
						"Não é foi possivel localizar a empresa de código " + id + "."));
	}
}
