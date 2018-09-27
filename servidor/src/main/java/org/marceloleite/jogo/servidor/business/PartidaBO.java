package org.marceloleite.jogo.servidor.business;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.PartidaDAO;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.util.ZonedDateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class PartidaBO {

	@Inject
	private ZonedDateTimeUtil zonedDateTimeUtil;

	@Inject
	private PartidaDAO partidaDAO;

	private Partida partida;

	public Partida obter() {
		if (partida == null) {
			partida = criar();
		}
		return partida;
	}

	private Partida criar() {

		return partidaDAO.salvar(Partida.builder()
				.instante(zonedDateTimeUtil.now())
				.build());
	}
}
