package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.EmpresaRepository;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaDAO implements BaseDAO<Empresa, Long> {

	@Inject
	private EmpresaRepository empresaRepository;

	@Override
	public Empresa salvar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	@Override
	public Optional<Empresa> obterPorId(Long id) {
		return empresaRepository.findById(id);
	}

	@Override
	public Iterable<Empresa> obterTodos() {
		return empresaRepository.findAll();
	}

	@Override
	public boolean excluir(Long id) {
		Optional<Empresa> optionalEmpresa = obterPorId(id);
		optionalEmpresa.ifPresent(empresaRepository::delete);
		return optionalEmpresa.isPresent();
	}
}
