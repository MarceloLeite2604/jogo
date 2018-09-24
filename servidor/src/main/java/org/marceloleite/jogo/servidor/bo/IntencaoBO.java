package org.marceloleite.jogo.servidor.bo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.IntencaoDAO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.StatusIntencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.marceloleite.jogo.servidor.modelo.comparador.ComparadorIntencaoMaiorPreco;
import org.marceloleite.jogo.servidor.modelo.comparador.ComparadorIntencaoMenorPreco;
import org.marceloleite.jogo.servidor.modelo.predicado.PredicadoIntencaoMaiorPreco;
import org.marceloleite.jogo.servidor.modelo.predicado.PredicadoIntencaoMenorPreco;
import org.marceloleite.jogo.servidor.util.IterableUtil;
import org.springframework.stereotype.Component;

@Component
public class IntencaoBO implements BaseBO<Intencao, Long> {

	@Inject
	private IntencaoDAO intencaoDAO;

	@Inject
	private PartidaBO partidaBO;

	@Inject
	private ContratoBO contratoBO;

	@Inject
	private IterableUtil iterableUtil;

	@Override
	public Intencao salvar(Intencao intencao) {
		return intencaoDAO.salvar(intencao);
	}

	@Override
	public Optional<Intencao> obterPorId(Long id) {
		return intencaoDAO.obterPorId(id);
	}

	@Override
	public Iterable<Intencao> obterTodos() {
		return intencaoDAO.obterTodos();
	}

	@Override
	public boolean excluir(Long id) {
		return intencaoDAO.excluir(id);
	}

	public List<Intencao> obterPorTipoStatus(TipoIntencao tipo, StatusIntencao status) {
		return iterableUtil.toList(intencaoDAO.obterPorPartidaTipoStatus(partidaBO.obter(), tipo, status));
	}

	public Object obterPorIdOuLancarExcecao(Long id) {
		Intencao intencao = intencaoDAO.obterPorId(id)
				.orElseThrow(() -> new JogoRegraNegocioException("Não existe uma intenção com id " + id + "."));

		verificarPartida(intencao);

		return intencao;
	}

	private void verificarPartida(Intencao intencao) {
		if (!partidaBO.obter()
				.equals(intencao.getEmpresa()
						.getPartida())) {
			throw new JogoRegraNegocioException("A intenção " + intencao.getId() + " não faz parte desta partida.");
		}
	}

	public List<Intencao> obterIntencoesAbertasCobertas(Intencao intencao) {
		TipoIntencao tipoProcurado = verificarTipoProcurado(intencao);
		List<Intencao> intencoes = obterPorTipoStatus(tipoProcurado, StatusIntencao.ABERTO);
		return intencoes.stream()
				.filter(obterFiltroPrecoUnitario(intencao))
				.filter(i -> !i.getEmpresa()
						.equals(intencao.getEmpresa()))
				.sorted(obterComparadorPreco(intencao))
				.collect(Collectors.toList());
	}

	private Comparator<Intencao> obterComparadorPreco(Intencao intencao) {
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			return new ComparadorIntencaoMenorPreco();
		} else {
			return new ComparadorIntencaoMaiorPreco();
		}
	}

	private Predicate<Intencao> obterFiltroPrecoUnitario(Intencao intencao) {
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			return new PredicadoIntencaoMaiorPreco(intencao.getPrecoUnitario());
		} else {
			return new PredicadoIntencaoMenorPreco(intencao.getPrecoUnitario());
		}
	}

	private TipoIntencao verificarTipoProcurado(Intencao intencao) {
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			return TipoIntencao.DEMANDA;
		} else {
			return TipoIntencao.OFERTA;
		}
	}

	public void verificarPreenchimento(Intencao intencao) {

		if (intencao.getQuantidade()
				.compareTo(contratoBO.calcularQuantidadeTotal(intencao.getContratos())) == 0) {
			intencao.setStatus(StatusIntencao.FECHADO);
		}

	}
}
