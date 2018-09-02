package org.marceloleite.jogo.servidor.atualizador;

import java.math.BigDecimal;
import java.util.Map;

import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class AtualizadorModeloIntencao {

	public void atualizarModeloParaCriacao(Intencao intencao) {
		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			atualizarModeloParaCriacaoOferta(intencao);
		} else {
			atualizarModeloParaCriacaoDemanda(intencao);
		}
	}

	private void atualizarModeloParaCriacaoOferta(Intencao intencao) {
		Empresa empresa = intencao.getEmpresa();
		
		Map<Produto, BigDecimal> estoque = empresa
				.getEstoque();

		Produto produto = intencao.getProduto();

		estoque.put(produto, estoque.get(produto)
				.subtract(intencao.getQuantidadeAtual()));
		empresa.getOfertas().add(intencao);
	}
	

	private void atualizarModeloParaCriacaoDemanda(Intencao intencao) {
		Empresa empresa = intencao.getEmpresa();
		empresa.setCaixa(empresa.getCaixa()
				.subtract(intencao.getPrecoTotalAtual()));
		empresa.getDemandas().add(intencao);
	}
}
