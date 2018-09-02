package org.marceloleite.jogo.servidor.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Contrato;

public class ContratoDAO implements BaseDAO<Contrato, Long> {
	
	private Map<Long, Contrato> contratos = new HashMap<>();

	@Override
	public Contrato salvar(Contrato contrato) {
		contratos.put(contrato.getId(), contrato);
		return contrato;
	}

	@Override
	public Optional<Contrato> obterPorId(Long id) {
		return Optional.ofNullable(contratos.get(id));
	}

	@Override
	public Collection<Contrato> obterTodos() {
		return contratos.values();
	}

	@Override
	public boolean excluir(Long id) {
		return Optional.ofNullable(contratos.remove(id))
				.isPresent();
	}

}
