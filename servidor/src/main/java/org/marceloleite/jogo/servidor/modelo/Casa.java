package org.marceloleite.jogo.servidor.modelo;

import java.util.HashMap;
import java.util.Map;

public class Casa {

	private Map<Long, Intencao> ofertasAbertas = new HashMap<>();

	private Map<Long, Intencao> ofertasFechadas = new HashMap<>();

	private Map<Long, Intencao> demandasAbertas = new HashMap<>();

	private Map<Long, Intencao> demandasFecahdas = new HashMap<>();

}
