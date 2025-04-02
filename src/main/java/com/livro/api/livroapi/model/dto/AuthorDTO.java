package com.livro.api.livroapi.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AuthorDTO(
	@NotEmpty(message = "Name required")
	String name,
	@NotNull(message = "Date Birth required")
	LocalDate dateBirth,
	@NotEmpty(message = "Nationality required")
	String nationality) {
	
}
