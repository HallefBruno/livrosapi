package com.livro.api.livroapi.dto.book;

public record FiltersBookDTO(
	String isbn, 
	String title, 
	String nameAuthor, 
	Integer yearPublication) {

}
