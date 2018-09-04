package org.marceloleite.jogo.servidor.gerador.id;

public class GeradorIdSequencial implements GeradorId<Long> {
	
	private long id = 0L;

	@Override
	public Long gerar() {
		return id++;
	}

}
