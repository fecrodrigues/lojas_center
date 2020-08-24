package br.com.fiap.exceptions;

public class NotFoundPedidoException extends RuntimeException {
	private static final long serialVersionUID = -2701739569108385565L;

	public NotFoundPedidoException(String message) {
		super(message);
	}
}
