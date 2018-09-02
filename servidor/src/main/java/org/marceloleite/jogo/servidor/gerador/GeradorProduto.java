package org.marceloleite.jogo.servidor.gerador;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.ProdutoBO;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class GeradorProduto {

	@Inject
	private ProdutoBO produtoBO;

	public void gerar(List<String> nomeProdutos) {
		nomeProdutos.forEach(this::criarProduto);
	}

	private void criarProduto(String nome) {
		produtoBO.salvar(Produto.builder()
				.nome(nome)
				.build());
	}
}
