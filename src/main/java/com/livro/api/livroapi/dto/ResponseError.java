package com.livro.api.livroapi.dto;

import java.util.Collections;
import java.util.List;

public record ResponseError(int status, String message, List<FieldMessage> fieldMessages) {
	
	public static ResponseError responseMessageBadRequest(int status, String message) {
		return new ResponseError(status, message, Collections.emptyList());
	}
	
	public static ResponseError responseMessageConflict(int status, String message) {
		return new ResponseError(status, message, Collections.emptyList());
	}
	
}
