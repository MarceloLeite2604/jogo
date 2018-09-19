package org.marceloleite.jogo.servidor.bo;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.ProdutoDAO;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoBO {

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private PartidaBO partidaBO;

//	public Produto salvar(Produto produto) {
//		return produtoDAO.salvar(produto);
//	}

	public Optional<Produto> obterPorId(Long id) {
		return produtoDAO.obterPorId(id);
	}

	public Iterable<Produto> obterTodos() {
		return produtoDAO.obterTodos();
	}

//	public boolean excluir(Long id) {
//		return produtoDAO.excluir(id);
//	}

	public Produto obterOuCriar(String nome) {
		Optional<Produto> optionalProduto = produtoDAO.obterPorNome(nome);

		if (optionalProduto.isPresent()) {
			return optionalProduto.get();
		} else {
			return produtoDAO.salvar(criarProduto(nome));
		}
	}

	private Produto criarProduto(String nome) {
		return Produto.builder()
				.partida(partidaBO.obter())
				.nome(nome)
				.build();
	}

}
