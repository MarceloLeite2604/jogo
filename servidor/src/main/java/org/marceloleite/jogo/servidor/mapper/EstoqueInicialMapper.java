package org.marceloleite.jogo.servidor.mapper;

import java.util.Map;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.ProdutoDAO;
import org.marceloleite.jogo.servidor.modelo.Produto;

public class EstoqueInicialMapper {

	@Inject
	private ProdutoDAO produtoDAO;

	public Map<Produto, Long> map(Map<String, Long> produtos) {
		// TODO Concluir
		return null;
	}
}
