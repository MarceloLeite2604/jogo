package org.marceloleite.jogo.iface.business;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.marceloleite.jogo.iface.dao.IntencaoDAO;
import org.marceloleite.jogo.iface.modelo.Intencao;
import org.marceloleite.jogo.iface.modelo.TipoIntencao;
import org.springframework.stereotype.Component;

@Component
public class IntencaoBO {

	@Inject
	private IntencaoDAO intencaoDAO;

	public List<Intencao> obterTodas() {
		return intencaoDAO.obterTodas();
	}

	public List<Intencao> obterOfertas(List<Intencao> intencoes) {
		return obterPorTipo(intencoes, TipoIntencao.OFERTA);
	}

	public List<Intencao> obterDemandas(List<Intencao> intencoes) {
		return obterPorTipo(intencoes, TipoIntencao.DEMANDA);
	}

	private List<Intencao> obterPorTipo(List<Intencao> intencoes, TipoIntencao tipoIntencao) {

		if (CollectionUtils.isEmpty(intencoes)) {
			return Collections.emptyList();
		}

		return intencoes.stream()
				.filter(intencao -> tipoIntencao.equals(intencao.getTipo()))
				.collect(Collectors.toList());
	}
}
