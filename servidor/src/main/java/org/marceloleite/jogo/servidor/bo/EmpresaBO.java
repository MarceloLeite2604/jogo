package org.marceloleite.jogo.servidor.bo;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.marceloleite.jogo.servidor.dao.EmpresaDAO;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
import org.marceloleite.jogo.servidor.modelo.ItemEstoqueId;
import org.marceloleite.jogo.servidor.modelo.TipoEmpresa;
import org.marceloleite.jogo.servidor.modelo.requisicao.RequisicaoEmpresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO {

	@Inject
	private EmpresaDAO empresaDAO;
	
	@Inject
	private PartidaBO partidaBO;

	@Inject
	private ItemEstoqueBO itemEstoqueBO;

	@Inject
	private Configuracao configuracao;

	public Empresa criar(RequisicaoEmpresa requisicaoEmpresa) {

		Empresa empresa = Empresa.builder()
				.partida(partidaBO.obter())
				.caixa(BigDecimal.valueOf(configuracao.getCaixaInicialEmpresa()))
				.nome(requisicaoEmpresa.getNome())
				.tipo(TipoEmpresa.JOGADOR)
				.estoque(new LinkedList<>())
				.ofertas(new LinkedList<>())
				.demandas(new LinkedList<>())
				.build();

		empresa.getEstoque()
				.addAll(itemEstoqueBO.criarEstoqueInicial(empresa));

		return empresaDAO.salvar(empresa);
	}

	public Empresa salvar(Empresa empresa) {
		return empresaDAO.salvar(empresa);
	}

	public Optional<Empresa> obterPorId(Long id) {
		return empresaDAO.obterPorId(id);
	}

	public Iterable<Empresa> obterTodos() {
		return empresaDAO.obterTodos();
	}

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
		ItemEstoqueId id = ItemEstoqueId.builder()
				.empresa(empresa)
				.produto(intencao.getProduto())
				.build();

		return ItemEstoque.builder()
				.id(id)
				.quantidade(intencao.getQuantidade())
				.build();
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
	}

	public Empresa obterOuCriar(Empresa empresa) {
		Optional<Empresa> optionalEmpresa = empresaDAO.obterPorPartidaNome(empresa.getPartida(), empresa.getNome());

		if (optionalEmpresa.isPresent()) {
			return optionalEmpresa.get();
		} else {
			return empresaDAO.salvar(empresa);
		}
	}
}
