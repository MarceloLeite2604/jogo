package org.marceloleite.jogo.servidor.modelo;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.bo.EmpresaBO;
import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.springframework.stereotype.Component;

@Component
public class Jogo {

	@Inject
	private EmpresaBO empresaBO;

	private Casa casa;

	@Inject
	private Configuracao configuracao;

	public void inicializar() {
		criarFornecedores();
		criarClientes();

	}

	private void criarFornecedores() {

		configuracao.getFornecedores()
				.stream()
				.forEach(nomeFornecedor -> empresaBO.salvar(criarEmpresa(nomeFornecedor, TipoEmpresa.FORNECEDOR)));
	}

	private void criarClientes() {

		configuracao.getClientes()
				.stream()
				.forEach(nomeCliente -> empresaBO.salvar(criarEmpresa(nomeCliente, TipoEmpresa.FORNECEDOR)));
	}

	private Empresa criarEmpresa(String nome, TipoEmpresa tipo) {
		return Empresa.builder()
				.tipo(tipo)
				.caixa(BigDecimal.ZERO)
				.nome(nome)
				.build();
	}
}
