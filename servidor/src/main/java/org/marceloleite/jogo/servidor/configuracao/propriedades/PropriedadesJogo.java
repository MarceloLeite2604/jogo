package org.marceloleite.jogo.servidor.configuracao.propriedades;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Valid
@Configuration
@ConfigurationProperties("jogo")
public class PropriedadesJogo {

	@NotNull
	private List<String> fornecedores = new ArrayList<>();
	
	@NotNull
	private List<String> clientes = new ArrayList<>();
	
	@NotNull
	@Min(0)
	private Double caixaInicialEmpresa;
	
	@Inject
	private PropriedadesProdutos propriedadesProdutos;
	
	public List<String> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<String> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Double getCaixaInicialEmpresa() {
		return caixaInicialEmpresa;
	}

	public void setCaixaInicialEmpresa(Double caixaInicialEmpresa) {
		this.caixaInicialEmpresa = caixaInicialEmpresa;
	}

	public List<String> getClientes() {
		return clientes;
	}

	public void setClientes(List<String> clientes) {
		this.clientes = clientes;
	}
	
	public PropriedadesProdutos getPropriedadesProdutos() {
		return propriedadesProdutos;
	}

}
