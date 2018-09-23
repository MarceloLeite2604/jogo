package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.ContratoRepository;
import org.marceloleite.jogo.servidor.modelo.Contrato;
import org.springframework.stereotype.Component;

@Component
public class ContratoDAO implements BaseDAO<Contrato, Long> {

	@Inject
	private ContratoRepository contratoRepository;

	@Override
	public Contrato salvar(Contrato contrato) {
		return contratoRepository.save(contrato);
	}

	@Override
	public Optional<Contrato> obterPorId(Long id) {
		return contratoRepository.findById(id);
	}

	@Override
	public Iterable<Contrato> obterTodos() {
		return  contratoRepository.findAll();
	}

	@Override
	public boolean excluir(Long id) {
		Optional<Contrato> optionalContrato = obterPorId(id);
		optionalContrato.ifPresent(contratoRepository::delete);
		return optionalContrato.isPresent();
	}

}
