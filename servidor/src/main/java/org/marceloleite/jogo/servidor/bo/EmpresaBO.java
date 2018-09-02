package org.marceloleite.jogo.servidor.bo;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.EmpresaDAO;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO implements BaseBO<Empresa, UUID> {

	@Inject
	private EmpresaDAO empresaDAO;

	@Override
	public Empresa salvar(Empresa empresa) {
		return empresaDAO.salvar(empresa);
	}

	@Override
	public Optional<Empresa> obterPorId(UUID id) {
		return empresaDAO.obterPorId(id);
	}

	@Override
	public Collection<Empresa> obterTodos() {
		return empresaDAO.obterTodos();
	}

	@Override
	public boolean excluir(UUID id) {
		return empresaDAO.excluir(id);
	}
}
