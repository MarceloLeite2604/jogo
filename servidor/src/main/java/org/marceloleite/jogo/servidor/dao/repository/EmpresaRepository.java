package org.marceloleite.jogo.servidor.dao.repository;

import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

}
