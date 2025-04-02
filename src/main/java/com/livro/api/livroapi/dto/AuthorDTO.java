package com.livro.api.livroapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record AuthorDTO(
	@NotEmpty(message = "Name required")
	String name,
	@NotNull(message = "Date Birth required")
	LocalDate dateBirth,
	@NotEmpty(message = "Nationality required")
	String nationality,
	List<BookDTO> books) {
	
	public static AuthorDTO of(String name, LocalDate dateBirth, String nationality) {
		return new AuthorDTO(name, dateBirth, nationality, null);
	}
}
