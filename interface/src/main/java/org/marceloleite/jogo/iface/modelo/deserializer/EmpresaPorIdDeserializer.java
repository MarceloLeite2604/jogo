package org.marceloleite.jogo.iface.modelo.deserializer;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import org.marceloleite.jogo.iface.business.EmpresaBO;
import org.marceloleite.jogo.iface.modelo.Empresa;
import org.marceloleite.jogo.iface.modelo.FormaBusca;

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
		return Optional.ofNullable(empresaBO.obter(id, FormaBusca.SOMENTE_CACHE))
				.orElse(criarEmpresaVazia(id));
	}

	private Empresa criarEmpresaVazia(long id) {
		return Empresa.builder()
				.id(id)
				.build();
	}
}
