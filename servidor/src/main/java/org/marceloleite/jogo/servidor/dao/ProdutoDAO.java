package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.ProdutoRepository;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDAO implements BaseDAO<Produto, Long> {
	
	@Inject
	private ProdutoRepository produtoRepository;

	@Override
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Optional<Produto> obterPorId(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Iterable<Produto> obterTodos() {
		return produtoRepository.findAll();
	}

	@Override
	public boolean excluir(Long id) {
		Optional<Produto> optionalProduto = obterPorId(id);
		optionalProduto.ifPresent(produtoRepository::delete);
		return optionalProduto.isPresent();
	}

	public Optional<Produto> obterPorNome(String nome) {
		return produtoRepository.findOptionalByNome(nome);
	}

	public Iterable<Empresa> obterPorPartida(Partida partida) {
		return produtoRepository.findByPartida(partida);
	}

	public Optional<Produto> obterPorPartidaNome(Partida partida, String nome) {
		return produtoRepository.findByPartidaAndNome(partida, nome);
	}
}
