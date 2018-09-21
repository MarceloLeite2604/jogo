package org.marceloleite.jogo.servidor.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.repository.IntencaoRepository;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.StatusIntencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.marceloleite.jogo.servidor.util.IterableUtil;
import org.springframework.stereotype.Component;

@Component
public class IntencaoDAO implements BaseDAO<Intencao, Long> {

	@Inject
	private IntencaoRepository intencaoRepository;

	@Inject
	private IterableUtil iterableUtil;

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

	public List<Intencao> obterPorTipo(TipoIntencao tipo) {
		return iterableUtil.toList(obterTodos())
				.stream()
				.filter(intencao -> tipo.equals(intencao.getTipo()))
				.collect(Collectors.toList());
	}

	public List<Intencao> obterContratosAbertos(TipoIntencao tipo) {
		return obterPorTipo(tipo).stream()
				.filter(intencao -> StatusIntencao.ABERTO.equals(intencao.getStatus()))
				.collect(Collectors.toList());
	}

}
