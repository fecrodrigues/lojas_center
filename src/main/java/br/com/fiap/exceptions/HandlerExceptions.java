package br.com.fiap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = NotFoundPedidoException.class)
	public ResponseEntity<?> handlerNotFoundPedidoException(NotFoundPedidoException ex){
		return new ResponseEntity<String>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NotCreatedPedidoException.class)
	public ResponseEntity<?> handlerNotCreatedPedidoException(NotCreatedPedidoException ex){
		return new ResponseEntity<String>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
