package com.livro.api.livroapi.exception;

import com.livro.api.livroapi.dto.FieldMessage;
import com.livro.api.livroapi.dto.ResponseError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<FieldMessage> fieldMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			fieldMessages.add(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
		}
        return new ResponseEntity<>(new ResponseError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", fieldMessages), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> responseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ResponseError.responseMessageBadRequest(HttpStatus.BAD_REQUEST.value(), ex.getReason()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<?> conflictExceptionException(ConflictException ex) {
		var responseError = ResponseError.responseMessageConflict(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(responseError, new HttpHeaders(), responseError.status());
    }

}
