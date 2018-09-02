package org.marceloleite.jogo.servidor.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.springframework.stereotype.Component;

@Component
public class IntencaoDAO implements BaseDAO<Intencao, Long> {
	
	private Map<Long, Intencao> intencoes;

	@Override
	public Intencao salvar(Intencao intencao) {
		intencoes.put(intencao.getId(), intencao);
		return intencao;
	}

	@Override
	public Optional<Intencao> obterPorId(Long id) {
		return Optional.ofNullable(intencoes.get(id));
	}

	@Override
	public Collection<Intencao> obterTodos() {
		return intencoes.values();
	}

	@Override
	public boolean excluir(Long id) {
		return Optional.ofNullable(intencoes.remove(id))
				.isPresent();
	}

}
