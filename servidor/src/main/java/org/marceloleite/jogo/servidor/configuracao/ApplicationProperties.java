package org.marceloleite.jogo.servidor.configuracao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jogo")
public class ApplicationProperties {

	@NotNull
	private List<String> fornecedores = new ArrayList<>();
	
	@NotNull
	private List<String> clientes = new ArrayList<>();
	
	@NotNull
	private Map<Long, Long> quantidadesIniciaisEstoque = new HashMap<>();
	
	@NotNull
	@Min(0)
	private Double caixaInicialEmpresa;
	
	@NotNull
	private List<String> produtos;

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

	public Map<Long, Long> getQuantidadesIniciaisEstoque() {
		return quantidadesIniciaisEstoque;
	}

	public void setQuantidadesIniciaisEstoque(Map<Long, Long> quantidadesIniciaisEstoque) {
		this.quantidadesIniciaisEstoque = quantidadesIniciaisEstoque;
	}

	public List<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}

}
