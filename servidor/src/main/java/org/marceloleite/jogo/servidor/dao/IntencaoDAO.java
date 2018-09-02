package org.marceloleite.jogo.servidor.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class IntencaoDAO implements BaseDAO<Intencao, Long> {

	private Map<Long, Intencao> intencoes = new HashMap<>();

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

	public List<Intencao> obterPorTipo(TipoIntencao tipo) {
		return obterTodos().stream()
				.filter(intencao -> tipo.equals(intencao.getTipo()))
				.collect(Collectors.toList());
	}

}
