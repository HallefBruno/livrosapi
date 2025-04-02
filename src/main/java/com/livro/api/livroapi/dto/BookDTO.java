package com.livro.api.livroapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BookDTO(
	@NotEmpty(message = "ISBN is required")
	String isbn, 
	@NotEmpty(message = "Name is required")
	String name, 
	@NotEmpty(message = "Title is required")
	String title, 
	@NotNull(message = "Date Publish is required")
	LocalDate datePublish, 
	@NotEmpty(message = "Genero is required")
	String genero, 
	@NotNull(message = "Price is required")
	BigDecimal price) {

}
