package com.livro.api.livroapi.dto;

import com.livro.api.livroapi.model.Author;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.BeanUtils;

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
	
	public static Author converter(AuthorDTO authorDTO) {
		Author author = new Author();
		BeanUtils.copyProperties(authorDTO, author);
		return author;
	}
	
}
