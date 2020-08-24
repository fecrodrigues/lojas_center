package br.com.fiap.exceptions;

public class NotCreatedPedidoException extends RuntimeException {
	private static final long serialVersionUID = -4067605932520217599L;

	public NotCreatedPedidoException(String message) {
		super(message);
	}
}
