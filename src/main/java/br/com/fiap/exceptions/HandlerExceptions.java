package br.com.fiap.exceptions;

import br.com.fiap.model.ErrorResponse;
import br.com.fiap.model.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = NotFoundPedidoException.class)
	public ResponseEntity<ErrorResponse> handlerNotFoundPedidoException(NotFoundPedidoException ex){
		ErrorResponse error = new ErrorResponse();
		error.setMessage("Não foi possivel encontrar o pedido");
		error.setMessage(ex.getCause().getMessage());
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NotCreatedPedidoException.class)
	public ResponseEntity<?> handlerNotCreatedPedidoException(NotCreatedPedidoException ex){
		ErrorResponse error = new ErrorResponse();
		error.setMessage("Error ao criar pedido");
		error.setMessage(ex.getCause().getMessage());
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();

		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(new Response<>("Algumas informações são inválidas", body), headers, status);

	}

	@ExceptionHandler(value = RollbackException.class)
	public ResponseEntity<?> handlerConstraintViolationException(RollbackException ex){

		Map<String, Object> body = new LinkedHashMap<>();
		List<String> errors;
		String message;

		try {
			ConstraintViolationException cve =  (ConstraintViolationException) ex.getCause();

			 errors = cve.getConstraintViolations()
					.stream()
					.map(x -> x.getMessage())
					.collect(Collectors.toList());

			 message = "Algumas informações são inválidas";
		} catch(Exception e) {
			errors = new ArrayList<>();
			errors.add(ex.getMessage());

			message = "Não foi possível executar a ação";
		}

		body.put("errors", errors);

		return new ResponseEntity<>(new Response<>(message, body), HttpStatus.BAD_REQUEST);
	}
}
