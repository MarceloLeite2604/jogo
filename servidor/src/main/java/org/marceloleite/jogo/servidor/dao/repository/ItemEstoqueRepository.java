package org.marceloleite.jogo.servidor.dao.repository;

import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.marceloleite.jogo.servidor.modelo.ItemEstoqueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEstoqueRepository extends CrudRepository<ItemEstoque, ItemEstoqueId>{

}
