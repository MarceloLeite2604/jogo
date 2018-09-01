package org.marceloleite.jogo.servidor.bo;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.ProdutoDAO;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoBO {

	@Inject
	private ProdutoDAO produtoDAO;

	public Produto salvar(Produto produto) {
		return produtoDAO.salvar(produto);
	}

}
