package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

public interface BaseDAO<T, I> {

	T salvar(T entidade);
	
	Optional<T> obterPorId(I id);
	
	Iterable<T> obterTodos();
	
	boolean excluir(I id);
}
