package org.marceloleite.jogo.servidor.dao.repository;

import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.StatusIntencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.springframework.data.repository.CrudRepository;

public interface IntencaoRepository extends CrudRepository<Intencao, Long> {

	public Iterable<Intencao> findByEmpresaPartidaAndTipo(Partida partida, TipoIntencao tipo);
	
	public Iterable<Intencao> findByEmpresaPartidaAndTipoAndStatus(Partida partida, TipoIntencao tipo, StatusIntencao status);

}
