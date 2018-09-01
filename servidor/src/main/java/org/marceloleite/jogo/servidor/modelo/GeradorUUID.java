package org.marceloleite.jogo.servidor.modelo;

import java.util.UUID;

public class GeradorUUID implements GeradorID<UUID> {

	@Override
	public UUID gerar() {
		return UUID.randomUUID();
	}

}
