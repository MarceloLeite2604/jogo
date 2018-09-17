package org.marceloleite.jogo.servidor.bo;

import java.util.Optional;

import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class ItemEstoqueBO {

	public Optional<ItemEstoque> obter(Empresa empresa, Produto produto) {
		return empresa.getEstoque()
				.stream()
				.filter(item -> item.getProduto()
						.equals(produto))
				.findFirst();
	}
	
	public Optional<ItemEstoque> obter(Intencao intencao) {
		return obter(intencao.getEmpresa(), intencao.getProduto());
	}
}
