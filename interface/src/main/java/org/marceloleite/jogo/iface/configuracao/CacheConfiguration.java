package org.marceloleite.jogo.iface.configuracao;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.modelo.Produto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

	@Bean
	public CacheManager createCacheManager() {
		return CacheManagerBuilder.newCacheManagerBuilder()
				.build(true);
	}

	@Bean
	public Cache<Long, Produto> criarCacheProduto(CacheManager cacheManager) {
		CacheConfigurationBuilder<Long, Produto> cacheConfigurationBuilder = criarCacheConfigurationBuilder(Long.class,
				Produto.class);
		return cacheManager.createCache("cacheProduto", cacheConfigurationBuilder);
	}

	@Bean
	public Cache<Long, Empresa> criarCacheEmpresa(CacheManager cacheManager) {
		CacheConfigurationBuilder<Long, Empresa> cacheConfigurationBuilder = criarCacheConfigurationBuilder(Long.class,
				Empresa.class);
		return cacheManager.createCache("cacheEmpresa", cacheConfigurationBuilder);
	}

	private <K, T> CacheConfigurationBuilder<K, T> criarCacheConfigurationBuilder(Class<K> keyClass,
			Class<T> valueClass) {
		return CacheConfigurationBuilder.newCacheConfigurationBuilder(keyClass, valueClass,
				ResourcePoolsBuilder.heap(100));
	}
}
