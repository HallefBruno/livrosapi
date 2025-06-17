package com.livro.api.livroapi.exception;

import com.livro.api.livroapi.dto.FieldMessage;
import com.livro.api.livroapi.dto.ResponseError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<FieldMessage> fieldMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			fieldMessages.add(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
		}
        return new ResponseEntity<>(new ResponseError(400, ex.getLocalizedMessage(), fieldMessages), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> responseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ResponseError.responseMessageBadRequest(ex.getReason()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
