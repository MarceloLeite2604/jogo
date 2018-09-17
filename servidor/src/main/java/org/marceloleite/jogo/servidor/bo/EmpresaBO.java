package org.marceloleite.jogo.servidor.bo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.EmpresaDAO;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO implements BaseBO<Empresa, Long> {

	@Inject
	private EmpresaDAO empresaDAO;

	@Inject
	private ItemEstoqueBO itemEstoqueBO;

	@Override
	public Empresa salvar(Empresa empresa) {
		return empresaDAO.salvar(empresa);
	}

	@Override
	public Optional<Empresa> obterPorId(Long id) {
		return empresaDAO.obterPorId(id);
	}

	@Override
	public Collection<Empresa> obterTodos() {
		return empresaDAO.obterTodos();
	}

	@Override
	public boolean excluir(Long id) {
		return empresaDAO.excluir(id);
	}

	public void atualizarEstoque(Empresa empresa, Intencao intencao) {
		Optional<ItemEstoque> optionalItemEstoque = itemEstoqueBO.obter(empresa, intencao.getProduto());
		if (optionalItemEstoque.isPresent()) {
			atualizarItemEstoque(intencao, optionalItemEstoque.get());
		} else {
			empresa.getEstoque()
					.add(criarItemEstoque(empresa, intencao));
		}
	}

	private ItemEstoque criarItemEstoque(Empresa empresa, Intencao intencao) {
		ItemEstoque itemEstoque = ItemEstoque.builder()
				.empresa(empresa)
				.produto(intencao.getProduto())
				.quantidade(intencao.getQuantidade())
				.build();
		return itemEstoque;
	}

	private void atualizarItemEstoque(Intencao intencao, ItemEstoque itemEstoque) {
		BigDecimal novaQuantidade = itemEstoque.getQuantidade()
				.subtract(intencao.getQuantidade());
		itemEstoque.setQuantidade(novaQuantidade);
	}

	public void atualizarOfertas(Empresa empresa, Intencao intencao) {
		empresa.getOfertas()
				.add(intencao);
	}

	public void atualizarCaixa(Empresa empresa, Intencao intencao) {
		empresa.setCaixa(empresa.getCaixa()
				.subtract(intencao.getPrecoTotalAtual()));
//		empresa.getDemandas()
//				.add(intencao);
	}
}
