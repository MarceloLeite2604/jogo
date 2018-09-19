package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.EmpresaRepository;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.TipoEmpresa;
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

	public long excluirPorTipo(TipoEmpresa tipo) {
		return empresaRepository.deleteByTipo(tipo);
	}

	public Optional<Empresa> obterPorPartidaNome(Partida partida, String nome) {
		return empresaRepository.findOptionalByPartidaAndNome(partida, nome);
	}
}
