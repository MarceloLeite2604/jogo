package org.marceloleite.jogo.iface.bean;

import java.io.Serializable;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.EmpresaBO;
import org.marceloleite.jogo.iface.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
		if (StringUtils.isEmpty(nomeEmpresa)) {
			throw new JogoRegraNegocioException("O nome da empresa não pode ser nulo.");
		}
		Empresa empresa = empresaBO.criar(nomeEmpresa);
	}
}
