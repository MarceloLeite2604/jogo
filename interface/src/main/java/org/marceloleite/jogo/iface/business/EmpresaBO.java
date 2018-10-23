package org.marceloleite.jogo.iface.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.ehcache.Cache;
import org.marceloleite.jogo.iface.dao.EmpresaDAO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.utils.CacheUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class EmpresaBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaDAO empresaDAO;

	@Inject
	private Cache<Long, Empresa> cacheEmpresa;

	public Empresa obter(long id) {
		return Optional.ofNullable(obterDaLista(obterTodos(), id))
				.orElse(obterDaLista(obterTodosDoServidor(), id));
	}

	private Empresa obterDaLista(List<Empresa> empresas, long id) {
		return empresas.stream()
				.filter(empresa -> empresa.getId() == id)
				.findFirst()
				.orElse(null);
	}

	public List<Empresa> obterTodos() {
		List<Empresa> produtos = CacheUtils.obterTodos(cacheEmpresa);
		if (CollectionUtils.isEmpty(produtos)) {
			produtos = obterTodosDoServidor();
		}
		return produtos;
	}

	private List<Empresa> obterTodosDoServidor() {
		List<Empresa> produtos;
		produtos = empresaDAO.obterTodos();
		inserirNaCache(produtos);
		return produtos;
	}

	private void inserirNaCache(List<Empresa> empresas) {
		empresas.forEach(empresa -> cacheEmpresa.put(empresa.getId(), empresa));
	}

	public Empresa criar(String nome) {
		return empresaDAO.criar(nome);
	}
}
