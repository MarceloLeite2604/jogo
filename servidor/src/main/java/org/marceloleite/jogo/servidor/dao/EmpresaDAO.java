package org.marceloleite.jogo.servidor.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaDAO implements BaseDAO<Empresa, UUID> {

	Map<UUID, Empresa> empresas = new HashMap<>();

	@Override
	public Empresa salvar(Empresa empresa) {
		empresas.put(empresa.getId(), empresa);
		return empresa;
	}

	@Override
	public Optional<Empresa> obterPorId(UUID id) {
		return Optional.ofNullable(empresas.get(id));
	}

	@Override
	public Collection<Empresa> obterTodos() {
		return empresas.values();
	}

	@Override
	public boolean excluir(UUID id) {
		return Optional.ofNullable(empresas.remove(id))
				.isPresent();
	}
}
