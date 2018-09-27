package org.marceloleite.jogo.iface.bean;

import java.io.Serializable;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.EmpresaBO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class PerguntaEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeEmpresa;

	@Inject
	private EmpresaBO empresaBO;

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public void aoPressionarBotaoCriar() {
		Empresa empresa = empresaBO.criar(nomeEmpresa);
	}
}
