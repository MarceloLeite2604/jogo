package org.marceloleite.jogo.servidor.business;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Partida;
import org.marceloleite.jogo.servidor.modelo.TipoEmpresa;
import org.springframework.stereotype.Component;

@Component
public class JogoBO {

	@Inject
	private EmpresaBO empresaBO;
	
	@Inject
	private PartidaBO partidaBO;

	@Inject
	private Configuracao configuracao;
	
	public void inicializar() {
		Partida partida = partidaBO.obter();
		criarFornecedores(partida);
		criarClientes(partida);
	}

	private void criarFornecedores(Partida partida) {
		criarEmpresas(partida, configuracao.getFornecedores(), TipoEmpresa.FORNECEDOR);
	}

	private void criarClientes(Partida partida) {
		criarEmpresas(partida, configuracao.getClientes(), TipoEmpresa.CLIENTE);
	}

	private void criarEmpresas(Partida partida, List<String> nomes, TipoEmpresa tipo) {
		for (String nome : nomes) {
			empresaBO.obterOuCriar(criarEmpresa(partida, nome, tipo));
		}
	}

	private Empresa criarEmpresa(Partida partida, String nome, TipoEmpresa tipo) {
		return Empresa.builder()
				.partida(partida)
				.tipo(tipo)
				.caixa(BigDecimal.ZERO)
				.nome(nome)
				.build();
	}
}
