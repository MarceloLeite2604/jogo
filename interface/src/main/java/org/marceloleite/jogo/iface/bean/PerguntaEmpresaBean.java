package org.marceloleite.jogo.iface.bean;

import java.io.Serializable;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.Pagina;
import org.marceloleite.jogo.iface.business.EmpresaBO;
import org.marceloleite.jogo.iface.excecao.JogoRegraNegocioException;
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

	@Inject
	private EmpresaBean empresaBean;

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String aoPressionarBotaoCriar() {
		if (StringUtils.isEmpty(nomeEmpresa)) {
			throw new JogoRegraNegocioException("O nome da empresa não pode ser nulo.");
		}
		empresaBean.setEmpresa(empresaBO.criar(nomeEmpresa));
		return Pagina.redirecionarPara(Pagina.EMPRESA);
	}
}
