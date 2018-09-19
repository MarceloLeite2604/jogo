package org.marceloleite.jogo.servidor.dao.repository;

import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.TipoEmpresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

	long deleteByTipo(TipoEmpresa tipo);

	Optional<Empresa> findOptionalByPartidaAndNome(Partida partida, String nome);

}
