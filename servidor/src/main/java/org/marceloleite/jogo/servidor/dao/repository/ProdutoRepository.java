package org.marceloleite.jogo.servidor.dao.repository;

import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

	Optional<Produto> findOptionalByNome(String nome);

	Iterable<Produto> findByPartida(Partida partida);

	Optional<Produto> findByPartidaAndNome(Partida partida, String nome);

}
