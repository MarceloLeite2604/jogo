package org.marceloleite.jogo.servidor.modelo;

public class GeradorIDSequencial implements GeradorID<Long> {
	
	private long id = 0L;

	@Override
	public Long gerar() {
		return id++;
	}

}
