package io.github.jotamath.eventmicroservice.exceptions;

public class EventNotFoundException extends RuntimeException {
	public EventNotFoundException() {
		super("Evento nao encontrado");
	}
	
	public EventNotFoundException(String message) {
		super(message);
	}
}
