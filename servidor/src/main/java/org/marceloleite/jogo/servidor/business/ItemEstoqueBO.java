package org.marceloleite.jogo.servidor.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.marceloleite.jogo.servidor.modelo.ItemEstoqueId;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ItemEstoqueBO {

	@Inject
	private Configuracao configuracao;

	@Inject
	private ProdutoBO produtoBO;

	public ItemEstoque obter(Empresa empresa, Produto produto) {
		return empresa.getEstoque()
				.stream()
				.filter(item -> item.getId()
						.getProduto()
						.equals(produto))
				.findFirst()
				.orElseThrow(() -> new JogoRegraNegocioException("Não foi possível localizar o produto "
						+ produto.getId() + " no estoque da empresa " + empresa.getId() + "."));
	}

	public List<ItemEstoque> criarEstoqueInicial(Empresa empresa) {
		List<ItemEstoque> estoque = new ArrayList<>(configuracao.getEstoqueInicial()
				.size());

		for (Entry<String, BigDecimal> entry : configuracao.getEstoqueInicial()
				.entrySet()) {

			estoque.add(criarItemEstoque(empresa, entry.getKey(), entry.getValue()));
		}
		return estoque;
	}

	private ItemEstoque criarItemEstoque(Empresa empresa, String nomeProduto, BigDecimal quantidadeInicial) {
		ItemEstoqueId id = ItemEstoqueId.builder()
				.produto(produtoBO.obterOuCriar(nomeProduto))
				.empresa(empresa)
				.build();

		return ItemEstoque.builder()
				.id(id)
				.quantidade(quantidadeInicial)
				.build();
	}
}
