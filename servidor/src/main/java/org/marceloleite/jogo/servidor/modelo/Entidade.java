package org.marceloleite.jogo.servidor.modelo;

import java.io.Serializable;

public interface Entidade<I extends Serializable> extends Serializable {

	I getId();
}
