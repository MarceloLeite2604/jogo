package org.marceloleite.jogo.iface.bean;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class FormatadorBean {

	public String formatarValorFinanceiro(BigDecimal valor) {
		return "$ " + valor.setScale(2)
				.toString();
	}
	
	public String formatarValor(BigDecimal valor) {
		return valor.setScale(2)
				.toString();
	}
}
