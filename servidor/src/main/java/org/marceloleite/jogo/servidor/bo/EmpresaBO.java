package org.marceloleite.jogo.servidor.bo;

import java.util.UUID;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.marceloleite.jogo.servidor.dao.EmpresaDAO;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO {

	@Inject
	private Configuracao configuracao;

	@Inject
	private EmpresaDAO empresaDAO;

	public Empresa salvar(Empresa empresa) {
		return empresaDAO.salvar(empresa);
	}

	public Empresa obter(UUID id) {
		return empresaDAO.obter(id);
	}
}
