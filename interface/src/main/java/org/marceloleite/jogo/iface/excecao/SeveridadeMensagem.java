package org.marceloleite.jogo.iface.excecao;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum SeveridadeMensagem {

	FATAL(FacesMessage.SEVERITY_FATAL, "Severity.Summary.Fatal"),
	ERRO(FacesMessage.SEVERITY_ERROR, "Severity.Summary.Error"),
	AVISO(FacesMessage.SEVERITY_WARN, "Severity.Summary.Warning"),
	INFORMACAO(FacesMessage.SEVERITY_INFO,"Severity.Summary.Information");
	
	private Severity severity;
	
	private String codigoMessagesSumario;
	
	private SeveridadeMensagem(Severity severity, String codigoMessagesSumario) {
		this.severity = severity;
		this.codigoMessagesSumario = codigoMessagesSumario;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getCodigoMessagesSumario() {
		return codigoMessagesSumario;
	}

	public void setCodigoMessagesSumario(String codigoMessagesSumario) {
		this.codigoMessagesSumario = codigoMessagesSumario;
	}
}
