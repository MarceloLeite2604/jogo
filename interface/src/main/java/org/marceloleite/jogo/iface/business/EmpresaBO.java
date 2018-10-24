package org.marceloleite.jogo.iface.business;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.ehcache.Cache;
import org.marceloleite.jogo.iface.dao.EmpresaDAO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.modelo.FormaBusca;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBO {

	@Inject
	private EmpresaDAO empresaDAO;

	@Inject
	private Cache<Long, Empresa> cacheEmpresa;

	public Empresa obter(long id) {
		return obter(id, FormaBusca.COMPLETA);
	}

	public Empresa obter(long id, FormaBusca formaBusca) {
		Optional<Empresa> optionalEmpresa = Optional.ofNullable(obterDaCache(id));
		if (!optionalEmpresa.isPresent() && FormaBusca.COMPLETA.equals(formaBusca)) {
			optionalEmpresa = Optional.ofNullable(obterDoServidor(id));
		}
		return optionalEmpresa.orElse(null);
	}

	public List<Empresa> obterTodos() {
		return obterDoServidor();
	}

	private Empresa obterDaCache(long id) {
		Empresa empresa = null;
		if (cacheEmpresa.containsKey(id)) {
			empresa = cacheEmpresa.get(id);
		}
		return empresa;
	}

	private List<Empresa> obterDoServidor() {
		List<Empresa> empresa = empresaDAO.obterTodos();
		inserirNaCache(empresa);
		return empresa;
	}

	private Empresa obterDoServidor(long id) {
		return obterDoServidor().stream()
				.filter(empresa -> empresa.getId() == id)
				.findFirst()
				.orElse(null);
	}

	private void inserirNaCache(List<Empresa> empresas) {
		empresas.forEach(empresa -> cacheEmpresa.put(empresa.getId(), empresa));
	}

	public Empresa criar(String nome) {
		return empresaDAO.criar(nome);
	}
}
