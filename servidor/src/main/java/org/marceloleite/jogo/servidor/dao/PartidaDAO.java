package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.PartidaRepository;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.springframework.stereotype.Component;

@Component
public class PartidaDAO implements BaseDAO<Partida, Long>{
	
	@Inject
	private PartidaRepository partidaRepository;

	@Override
	public Partida salvar(Partida entidade) {
		return partidaRepository.save(entidade);
	}

	@Override
	public Optional<Partida> obterPorId(Long id) {
		return partidaRepository.findById(id);
	}

	@Override
	public Iterable<Partida> obterTodos() {
		return partidaRepository.findAll();
	}

	@Override
	public boolean excluir(Long id) {
		Optional<Partida> optionalPartida = obterPorId(id);
		optionalPartida.ifPresent(partidaRepository::delete);
		return optionalPartida.isPresent();
	}

}
