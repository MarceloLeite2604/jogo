package org.marceloleite.jogo.servidor.modelo.comparador;

import java.util.Comparator;

import org.marceloleite.jogo.servidor.modelo.Intencao;

public class ComparadorIntencaoId implements Comparator<Intencao> {

	@Override
	public int compare(Intencao primeiro, Intencao segundo) {
		return primeiro.getId().compareTo(segundo.getId());
	}

}
