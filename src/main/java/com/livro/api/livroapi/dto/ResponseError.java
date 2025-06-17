package com.livro.api.livroapi.dto;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;


public record ResponseError(int status, String message, List<FieldMessage> fieldMessages) {
	
	public static ResponseError responseMessageBadRequest(String message) {
		return new ResponseError(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList());
	}
	
	public static ResponseError responseMessageConflict(String message) {
		return new ResponseError(HttpStatus.CONFLICT.value(), message, Collections.emptyList());
	}
	
}
