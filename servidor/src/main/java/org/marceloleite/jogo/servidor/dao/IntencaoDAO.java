package org.marceloleite.jogo.servidor.dao;

import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.IntencaoRepository;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.StatusIntencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class IntencaoDAO implements BaseDAO<Intencao, Long> {

	@Inject
	private IntencaoRepository intencaoRepository;

	@Override
	public Intencao salvar(Intencao intencao) {
		return intencaoRepository.save(intencao);
	}

	@Override
	public Optional<Intencao> obterPorId(Long id) {
		return intencaoRepository.findById(id);
	}

	@Override
	public Iterable<Intencao> obterTodos() {
		return intencaoRepository.findAll();
	}

	@Override
	public boolean excluir(Long id) {
		Optional<Intencao> optionalIntencao = obterPorId(id);

		if (optionalIntencao.isPresent()) {
			intencaoRepository.delete(optionalIntencao.get());
		}
		return optionalIntencao.isPresent();
	}

	public Iterable<Intencao> obterPorPartidaTipo(Partida partida, TipoIntencao tipo) {
		return intencaoRepository.findByEmpresaPartidaAndTipo(partida, tipo);
	}

	public Iterable<Intencao> obterPorPartidaTipoStatus(Partida partida, TipoIntencao tipo, StatusIntencao status) {
		return intencaoRepository.findByEmpresaPartidaAndTipoAndStatus(partida, tipo, status);
	}

}
