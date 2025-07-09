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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<FieldMessage> fieldMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			fieldMessages.add(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
		}
        return new ResponseEntity<>(new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", fieldMessages), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> responseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ResponseError.responseMessageBadRequest(HttpStatus.BAD_REQUEST.value(), ex.getReason()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseError conflictExceptionException(ConflictException ex) {
		return ResponseError.responseMessageConflict(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseError notFoundException(NotFoundException ex) {
		return ResponseError.responseMessageConflict(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

}
