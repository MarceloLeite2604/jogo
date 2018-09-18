package org.marceloleite.jogo.servidor.dao.repository;

import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

	Optional<Produto> findOptionalByNome(String nome);

}
