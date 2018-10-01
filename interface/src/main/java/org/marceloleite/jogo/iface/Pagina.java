package org.marceloleite.jogo.iface;

public final class Pagina {
	
	public static final String PERGUNTA_EMPRESA = "pergunta-empresa.xhtml";
	
	public static final String EMPRESA = "empresa.xhtml";
	
	public static final String REDIRECT = "?faces-redirect=true";

	private Pagina() {

	}
	
	public static String redirecionarPara(String pagina) {
		return pagina + REDIRECT;
	}

}
