package org.marceloleite.jogo.servidor.bo;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.configuracao.Configuracao;
import org.marceloleite.jogo.servidor.dao.EmpresaDAO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Empresa;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.ItemEstoque;
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

		verificarExisteEmpresaMesmoNome(requisicaoEmpresa.getNome());

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

	private void verificarExisteEmpresaMesmoNome(String nome) {
		if (empresaDAO.obterPorPartidaNome(partidaBO.obter(), nome)
				.isPresent()) {
			throw new JogoRegraNegocioException("Já existe uma empresa com o nome \"" + nome + "\".");
		}
	}

	public Empresa salvar(Empresa empresa) {
		return empresaDAO.salvar(empresa);
	}

	public Optional<Empresa> obterPorId(Long id) {
		return empresaDAO.obterPorId(id);
	}

	public Iterable<Empresa> obterPorPartida() {
		return empresaDAO.obterPorPartida(partidaBO.obter());
	}

	public boolean excluirDaPartidaAtual(Long id) {
		Empresa empresa = obterPorIdOuLancarExcecao(id);

		if (!isEmpresaNaPartida(empresa)) {
			throw new JogoRegraNegocioException("A empresa com o id " + id + " não faz parte da partida atual.");
		}

		if (!isEmpresaJogadora(empresa)) {
			throw new JogoRegraNegocioException("A empresa com o id " + id + " não é um jogador.");
		}

		return empresaDAO.excluir(id);
	}

	private boolean isEmpresaNaPartida(Empresa empresa) {
		return partidaBO.obter()
				.equals(empresa.getPartida());
	}

	private boolean isEmpresaJogadora(Empresa empresa) {
		return (empresa.getTipo() == TipoEmpresa.JOGADOR);
	}

	public Empresa obterPorIdOuLancarExcecao(Long id) {
		return empresaDAO.obterPorId(id)
				.orElseThrow(() -> new JogoRegraNegocioException("Não existe uma empresa com o id " + id + "."));
	}

	public void atualizarEstoque(Empresa empresa, Intencao intencao) {
		ItemEstoque itemEstoque = itemEstoqueBO.obter(empresa, intencao.getProduto());
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
