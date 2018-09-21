package org.marceloleite.jogo.servidor.bo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.dao.IntencaoDAO;
import org.marceloleite.jogo.servidor.excecao.JogoRegraNegocioException;
import org.marceloleite.jogo.servidor.modelo.Contrato;
import org.marceloleite.jogo.servidor.modelo.Intencao;
import org.marceloleite.jogo.servidor.modelo.StatusIntencao;
import org.marceloleite.jogo.servidor.modelo.TipoIntencao;
import org.marceloleite.jogo.servidor.modelo.comparador.ComparadorIntencaoMaiorPreco;
import org.marceloleite.jogo.servidor.modelo.comparador.ComparadorIntencaoMenorPreco;
import org.springframework.stereotype.Component;

@Component
public class IntencaoBO implements BaseBO<Intencao, Long> {

	@Inject
	private IntencaoDAO intencaoDAO;

	@Inject
	private PartidaBO partidaBO;

	@Inject
	private ContratoBO contratoBO;

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

	public List<Intencao> obterPorTipo(TipoIntencao tipo) {
		return intencaoDAO.obterPorTipo(tipo);
	}

	public List<Intencao> obterContratosAbertos(TipoIntencao tipo) {
		return intencaoDAO.obterContratosAbertos(tipo);

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
		return obterContratosAbertos(verificarTipoProcurado(intencao)).stream()
				.filter(intencaoObtida -> intencao.getPrecoUnitario()
						.compareTo(intencaoObtida.getPrecoUnitario()) >= 0)
				.filter(demanda -> !intencao.getEmpresa()
						.equals(demanda.getEmpresa()))
				.sorted(obterComparador(intencao))
				.collect(Collectors.toList());
	}

	private Comparator<Intencao> obterComparador(Intencao intencao) {
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			return new ComparadorIntencaoMaiorPreco();
		} else {
			return new ComparadorIntencaoMenorPreco();
		}
	}

	private TipoIntencao verificarTipoProcurado(Intencao intencao) {
		if (TipoIntencao.OFERTA.equals(intencao.getTipo())) {
			return TipoIntencao.DEMANDA;
		} else {
			return TipoIntencao.OFERTA;
		}
	}

	public void verificarPreenchimento(Intencao intencao) {
		List<Contrato> contratos;
		if (intencao.getTipo() == TipoIntencao.OFERTA) {
			contratos = intencao.getContratosDemanda();
		} else {
			contratos = intencao.getContratosOferta();
		}

		if (intencao.getQuantidade()
				.compareTo(contratoBO.calcularQuantidadeTotal(contratos)) == 0) {
			intencao.setStatus(StatusIntencao.FECHADO);
		}

	}
}
