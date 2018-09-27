package org.marceloleite.jogo.servidor.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.ContratoDAO;
import org.marceloleite.jogo.servidor.modelo.Contrato;
import org.springframework.stereotype.Component;

@Component
public class ContratoBO implements BaseBO<Contrato, Long> {

	@Inject
	private ContratoDAO contratoDAO;

	@Override
	public Contrato salvar(Contrato contrato) {
		return contratoDAO.salvar(contrato);
	}

	@Override
	public Optional<Contrato> obterPorId(Long id) {
		return contratoDAO.obterPorId(id);
	}

	@Override
	public Iterable<Contrato> obterTodos() {
		return contratoDAO.obterTodos();
	}

	@Override
	public boolean excluir(Long id) {
		return contratoDAO.excluir(id);
	}

	public BigDecimal calcularQuantidadeTotal(List<Contrato> contratos) {
		return contratos.stream()
				.map(Contrato::getQuantidade)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
