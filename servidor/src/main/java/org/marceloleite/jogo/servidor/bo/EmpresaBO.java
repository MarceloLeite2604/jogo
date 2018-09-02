package org.marceloleite.jogo.servidor.bo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.EmpresaDAO;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.Produto;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO implements BaseBO<Empresa, UUID> {

	@Inject
	private EmpresaDAO empresaDAO;

	@Override
	public Empresa salvar(Empresa empresa) {
		return empresaDAO.salvar(empresa);
	}

	@Override
	public Optional<Empresa> obterPorId(UUID id) {
		return empresaDAO.obterPorId(id);
	}

	@Override
	public Collection<Empresa> obterTodos() {
		return empresaDAO.obterTodos();
	}

	@Override
	public boolean excluir(UUID id) {
		return empresaDAO.excluir(id);
	}

	public void atualizarEstoque(Empresa empresa, Intencao intencao) {
		Map<Produto, BigDecimal> estoque = empresa.getEstoque();
		Produto produto = intencao.getProduto();
		estoque.put(produto, estoque.get(produto)
				.subtract(intencao.getQuantidadeAtual()));
		empresa.getOfertas()
				.add(intencao);
	}

	public void atualizarCaixa(Empresa empresa, Intencao intencao) {
		empresa.setCaixa(empresa.getCaixa()
				.subtract(intencao.getPrecoTotalAtual()));
		empresa.getDemandas()
				.add(intencao);
	}
}
