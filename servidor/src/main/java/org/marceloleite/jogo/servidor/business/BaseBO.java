package org.marceloleite.jogo.servidor.business;

import java.util.Optional;

public interface BaseBO<T, I> {

	T salvar(T entidade);

	Optional<T> obterPorId(I id);

	Iterable<T> obterTodos();

	boolean excluir(I id);
}
