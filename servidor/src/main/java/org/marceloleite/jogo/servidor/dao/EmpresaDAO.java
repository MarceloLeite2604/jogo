package org.marceloleite.jogo.servidor.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaDAO {
	
	Map<UUID, Empresa> empresas = new HashMap<>();

	public Empresa salvar(Empresa empresa) {
		empresas.put(empresa.getId(), empresa);
		return empresa;
	}

	public Empresa obter(UUID id) {
		return empresas.get(id);
	}
}
