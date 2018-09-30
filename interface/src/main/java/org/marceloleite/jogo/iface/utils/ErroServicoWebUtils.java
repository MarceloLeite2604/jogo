package org.marceloleite.jogo.iface.utils;

import java.io.IOException;

import org.apache.commons.collections4.CollectionUtils;
import org.marceloleite.jogo.iface.modelo.erroweb.DetalhamentoErroWeb;
import org.marceloleite.jogo.iface.modelo.erroweb.ErroServicoWeb;

public final class ErroServicoWebUtils {

	private ErroServicoWebUtils() {
	}

	public static String formatarMensagem(ErroServicoWeb erroServicoWeb) throws IOException {
		String prefixo = "";
		StringBuilder stringBuilder = new StringBuilder();
		if (!CollectionUtils.isEmpty(erroServicoWeb.getErrors())) {
			if (erroServicoWeb.getErrors()
					.size() == 1) {
				stringBuilder.append("[ Motivo: ");
			} else {
				stringBuilder.append("[ Motivos:\n");
				prefixo = "\t";
			}
			for (DetalhamentoErroWeb detalhamentoErroWeb : erroServicoWeb.getErrors()) {
				stringBuilder.append(
						prefixo + detalhamentoErroWeb.getObjectName() + "." + detalhamentoErroWeb.getField() + ": ");
				stringBuilder.append(detalhamentoErroWeb.getDefaultMessage());
			}
			stringBuilder.append(" ]\n");
		} else {
			throw new IOException("Não foi possível obter os motivos do erro.");
		}
		return stringBuilder.toString();
	}
}
