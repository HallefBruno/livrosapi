package com.livro.api.livroapi.dto.book;

import com.livro.api.livroapi.model.Genero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDTO(
	
	UUID id,	
	String isbn,
	String name,
	String title,
	LocalDate datePublish,
	Genero genero,
	BigDecimal price,
	AuthorResponseDTO author
		
	){
	
	public BookResponseDTO {}
}
