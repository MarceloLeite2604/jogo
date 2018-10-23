package org.marceloleite.jogo.iface.modelo.deserializer;

import java.io.IOException;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.EmpresaBO;
import org.marceloleite.jogo.iface.modelo.Empresa;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class EmpresaPorIdDeserializer extends JsonDeserializer<Empresa> {

	@Inject
	private EmpresaBO empresaBO;

	@Override
	public Empresa deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		long id = jsonParser.getValueAsLong();
		return empresaBO.obter(id);
	}
}
