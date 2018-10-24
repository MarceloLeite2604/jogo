package org.marceloleite.jogo.servidor.business;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.ProdutoDAO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoBO {

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private PartidaBO partidaBO;

	public Optional<Produto> obterPorId(Long id) {
		return produtoDAO.obterPorId(id);
	}

	public Iterable<Produto> obterTodos() {
		return produtoDAO.obterTodos();
	}

	public Produto obterOuCriar(String nome) {
		Optional<Produto> optionalProduto = produtoDAO.obterPorPartidaNome(partidaBO.obter(), nome);

		if (optionalProduto.isPresent()) {
			return optionalProduto.get();
		} else {
			return produtoDAO.salvar(criarProduto(nome));
		}
	}

	public Iterable<Produto> obterPorPartida() {
		return produtoDAO.obterPorPartida(partidaBO.obter());
	}

	public Produto obterPorIdOuLancarExcecao(Long id) {
		return produtoDAO.obterPorId(id)
				.orElseThrow(() -> new JogoRegraNegocioException("Não existe um produto com o id " + id + " nesta partida."));
	}

	private Produto criarProduto(String nome) {
		return Produto.builder()
				.partida(partidaBO.obter())
				.nome(nome)
				.build();
	}

}
