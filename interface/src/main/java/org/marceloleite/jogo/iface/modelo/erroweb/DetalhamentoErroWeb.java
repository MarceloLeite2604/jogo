package org.marceloleite.jogo.iface.modelo.erroweb;

import java.util.List;

public class DetalhamentoErroWeb {

	private List<String> codes;
	
	private List<ArgumentoErroWeb> arguments;
	
	private String defaultMessage;
	
	private String objectName;
	
	private String field;
	
	private String rejectedValue;
	
	private boolean bindingFailure;
	
	private String code;

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public List<ArgumentoErroWeb> getArguments() {
		return arguments;
	}

	public void setArguments(List<ArgumentoErroWeb> arguments) {
		this.arguments = arguments;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public boolean isBindingFailure() {
		return bindingFailure;
	}

	public void setBindingFailure(boolean bindingFailure) {
		this.bindingFailure = bindingFailure;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
