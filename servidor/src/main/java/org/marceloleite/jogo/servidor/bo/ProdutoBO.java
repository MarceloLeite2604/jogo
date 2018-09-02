package org.marceloleite.jogo.servidor.bo;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.ProdutoDAO;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoBO implements BaseBO<Produto, Long> {

	@Inject
	private ProdutoDAO produtoDAO;

	@Override
	public Produto salvar(Produto produto) {
		return produtoDAO.salvar(produto);
	}

	@Override
	public Optional<Produto> obterPorId(Long id) {
		return produtoDAO.obterPorId(id);
	}

	@Override
	public Collection<Produto> obterTodos() {
		return produtoDAO.obterTodos();
	}

	@Override
	public boolean excluir(Long id) {
		return produtoDAO.excluir(id);
	}

}
