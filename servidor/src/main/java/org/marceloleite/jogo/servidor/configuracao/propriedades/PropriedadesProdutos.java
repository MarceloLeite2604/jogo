package org.marceloleite.jogo.servidor.configuracao.propriedades;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Valid
@Configuration
@ConfigurationProperties("jogo.produtos")
public class PropriedadesProdutos {

	@NotNull
	private List<String> nome;
	
	@NotNull
	private List<Double> quantidadeInicial;

	public List<String> getNome() {
		return nome;
	}
	
	public void setNome(List<String> nome) {
		this.nome = nome;
	}

	public List<Double> getQuantidadeInicial() {
		return quantidadeInicial;
	}
	
	public void setQuantidadeInicial(List<Double> quantidadeInicial) {
		this.quantidadeInicial = quantidadeInicial;
	}
}
