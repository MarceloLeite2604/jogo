package org.marceloleite.jogo.servidor.modelo.comparador;

import java.util.Comparator;

import org.marceloleite.jogo.servidor.modelo.Intencao;

public class ComparadorIntencaoMaiorPreco implements Comparator<Intencao> {

	@Override
	public int compare(Intencao primeiro, Intencao segundo) {
		int comparacaoPrecoUnitario = segundo.getPrecoUnitario()
				.compareTo(primeiro.getPrecoUnitario());
		if (comparacaoPrecoUnitario != 0) {
			return comparacaoPrecoUnitario;
		} else {
			return new ComparadorIntencaoId().compare(primeiro, segundo);
		}
	}

}
