package org.marceloleite.jogo.iface.bean;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.IntencaoBO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.modelo.Intencao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmpresaBean {

	@Inject
	private IntencaoBO intencaoBO;
	
	private Empresa empresa;

	private List<Intencao> ofertas;

	private List<Intencao> demandas;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Intencao> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Intencao> ofertas) {
		this.ofertas = ofertas;
	}

	public List<Intencao> getDemandas() {
		return demandas;
	}

	public void setDemandas(List<Intencao> demandas) {
		this.demandas = demandas;
	}

	public void atualizarIntencoes() {
		List<Intencao> intencoes = intencaoBO.obterTodas();
		ofertas = intencaoBO.obterOfertas(intencoes);
		demandas = intencaoBO.obterDemandas(intencoes);
	}
}
