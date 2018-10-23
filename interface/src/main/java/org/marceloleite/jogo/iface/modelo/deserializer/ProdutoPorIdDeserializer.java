package org.marceloleite.jogo.iface.modelo.deserializer;

import java.io.IOException;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.ProdutoBO;
import org.marceloleite.jogo.iface.modelo.Produto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ProdutoPorIdDeserializer extends JsonDeserializer<Produto> {

	@Inject
	private ProdutoBO produtoBO;

	@Override
	public Produto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		long id = jsonParser.getValueAsLong();
		return produtoBO.obter(id);
	}
}
