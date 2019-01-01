package org.marceloleite.jogo.iface.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.ProdutoBO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.modelo.Produto;
import org.marceloleite.jogo.iface.modelo.TipoIntencao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class IntencaoBean {

	@Inject
	private ProdutoBO produtoBO;

	private Empresa empresa;

	private TipoIntencao tipoIntencao;

	private Produto produto;
	
	private BigDecimal preco;
	
	private BigDecimal quantidade;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoIntencao getTipoIntencao() {
		return tipoIntencao;
	}

	public void setTipoIntencao(TipoIntencao tipoIntencao) {
		this.tipoIntencao = tipoIntencao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public List<Produto> obterProdutos() {
		if (TipoIntencao.OFERTA.equals(tipoIntencao)) {
			return empresa.getEstoque()
					.stream()
					.map(item -> item.getId()
							.getProduto())
					.collect(Collectors.toList());
		} else {
			return produtoBO.obterTodos();
		}
	}
}
