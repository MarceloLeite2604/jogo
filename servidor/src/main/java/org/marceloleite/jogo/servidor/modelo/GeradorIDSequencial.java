package org.marceloleite.jogo.servidor.modelo;

public class GeradorIDSequencial implements GeradorID<Long> {
	
	private long id = 1L;

	@Override
	public Long gerar() {
		return id++;
	}

}
