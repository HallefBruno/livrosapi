package com.livro.api.livroapi.dto.book;

import java.time.LocalDate;


public record AuthorResponseDTO(String name, LocalDate dateBirth, String nationality) {
	
	
}
