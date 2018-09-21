package org.marceloleite.jogo.servidor.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class IterableUtil {

	public <T> List<T> toList(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		iterable.forEach(list::add);
		return list;
	}
}
