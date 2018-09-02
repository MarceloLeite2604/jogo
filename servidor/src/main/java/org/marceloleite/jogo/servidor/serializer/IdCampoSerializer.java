package org.marceloleite.jogo.servidor.serializer;

import java.io.IOException;
import java.io.Serializable;

import org.marceloleite.jogo.servidor.modelo.Entidade;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class IdCampoSerializer extends StdSerializer<Entidade<? extends Serializable>> {

	private static final long serialVersionUID = 1L;

	public IdCampoSerializer() {
		this(null);
	}

	public IdCampoSerializer(Class<Entidade<? extends Serializable>> clazz) {
		super(clazz);
	}

	@Override
	public void serialize(Entidade<? extends Serializable> entidade, JsonGenerator jsonGenerator,
			SerializerProvider provider) throws IOException {
		jsonGenerator.writeString(entidade.getId()
				.toString());
	}

}
