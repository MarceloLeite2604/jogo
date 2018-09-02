package org.marceloleite.jogo.servidor.bo;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.IntencaoDAO;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.springframework.stereotype.Component;

@Component
public class IntencaoBO implements BaseBO<Intencao, Long> {
	
	@Inject
	private IntencaoDAO intencaoDAO;

	@Override
	public Intencao salvar(Intencao intencao) {
		return intencaoDAO.salvar(intencao);
	}

	@Override
	public Optional<Intencao> obterPorId(Long id) {
		return intencaoDAO.obterPorId(id);
	}

	@Override
	public Collection<Intencao> obterTodos() {
		return intencaoDAO.obterTodos();
	}

	@Override
	public boolean excluir(Long id) {
		return intencaoDAO.excluir(id);
	}

}
