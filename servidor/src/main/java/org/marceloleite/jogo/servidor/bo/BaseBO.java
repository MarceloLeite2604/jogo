package org.marceloleite.jogo.servidor.bo;

import java.util.Collection;
import java.util.Optional;

public interface BaseBO<T, I> {

	T salvar(T entidade);

	Optional<T> obterPorId(I id);

	Collection<T> obterTodos();

	boolean excluir(I id);
}
