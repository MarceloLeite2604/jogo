package org.marceloleite.jogo.iface.modelo.erroweb;

import java.time.ZonedDateTime;
import java.util.List;

import org.marceloleite.libraries.time.deserializer.ZonedDateTimeFromTimestampDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ErroServicoWeb {

	@JsonDeserialize(using = ZonedDateTimeFromTimestampDeserializer.class)
	private ZonedDateTime timestamp;

	private int status;

	private String error;

	private List<DetalhamentoErroWeb> errors;

	private String message;

	private String path;

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<DetalhamentoErroWeb> getErrors() {
		return errors;
	}

	public void setErrors(List<DetalhamentoErroWeb> errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
