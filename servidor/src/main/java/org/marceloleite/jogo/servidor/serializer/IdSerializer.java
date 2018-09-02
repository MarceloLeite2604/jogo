package org.marceloleite.jogo.servidor.serializer;

import java.io.IOException;
import java.io.Serializable;

import org.marceloleite.jogo.servidor.modelo.Entidade;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IdSerializer extends JsonSerializer<Entidade<? extends Serializable>> {

	@Override
	public void serialize(Entidade<? extends Serializable> entidade, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeFieldName(entidade.getId().toString());

	}

}
