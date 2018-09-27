package org.marceloleite.jogo.iface.business;

import java.io.Serializable;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.dao.EmpresaDAO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO empresaDAO;

	public Empresa criar(String nome) {
		return empresaDAO.criar(nome);
	}
}
