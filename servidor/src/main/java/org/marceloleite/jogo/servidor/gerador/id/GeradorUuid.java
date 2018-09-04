package org.marceloleite.jogo.servidor.gerador.id;

import java.util.UUID;

public class GeradorUuid implements GeradorId<UUID> {

	@Override
	public UUID gerar() {
		return UUID.randomUUID();
	}

}
