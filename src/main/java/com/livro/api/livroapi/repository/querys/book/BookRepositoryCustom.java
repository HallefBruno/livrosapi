package com.livro.api.livroapi.repository.querys.book;

import com.livro.api.livroapi.dto.book.BookResponseDTO;
import com.livro.api.livroapi.dto.book.FiltersBookDTO;
import org.springframework.data.domain.Page;


public interface BookRepositoryCustom {
	
	public Page<BookResponseDTO> pageBooks(FiltersBookDTO filtersBookDTO);
	public Page<BookResponseDTO> pageBooksV2(FiltersBookDTO filtersBookDTO);
	
}
