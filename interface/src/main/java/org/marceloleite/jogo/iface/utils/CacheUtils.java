package org.marceloleite.jogo.iface.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.ehcache.Cache;

public final class CacheUtils {

	private CacheUtils() {
	}

	public static <T> List<T> obterTodos(Cache<?, T> cache) {
		return StreamSupport.stream(cache.spliterator(), false)
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}
}
