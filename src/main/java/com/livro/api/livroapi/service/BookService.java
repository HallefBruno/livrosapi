package com.livro.api.livroapi.service;

import com.livro.api.livroapi.dto.book.BookDTO;
import com.livro.api.livroapi.dto.book.BookResponseDTO;
import com.livro.api.livroapi.dto.book.FiltersBookDTO;
import com.livro.api.livroapi.exception.NotFoundException;
import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.repository.BookRepository;
import com.livro.api.livroapi.repository.specification.BookSpecification;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Transactional
	public String salvar(BookDTO bookDTO) {
		Book book = new Book();
		BeanUtils.copyProperties(bookDTO, book);
		return bookRepository.save(book).getId().toString();
	}
	
	@Transactional
	public void update(String uuid, BookDTO book) {
		Book bookAtual = bookRepository.findById(UUID.fromString(uuid)).get();
		BeanUtils.copyProperties(book, bookAtual, "id");
		bookRepository.save(bookAtual);
	}
	
	@Transactional
	public void delete(String uuid) {
		Optional<Book> optional = bookRepository.findById(UUID.fromString(uuid));
		if(optional.isPresent()) {
			bookRepository.deleteById(UUID.fromString(uuid));
			return;
		}
		throw new NotFoundException("Book not found!");
	}
	
	public Book getBook(String uuid) {
		return bookRepository.getReferenceById(UUID.fromString(uuid));
	}
	
	public List<Book> getAll(String orderBy) {
		if(orderBy == null) orderBy = "id";
		return bookRepository.findAll(Sort.by(orderBy));
	}
	
	public Page<BookResponseDTO> pageBooks(FiltersBookDTO filtersBookDTO) {
		return bookRepository.pageBooks(filtersBookDTO);
	}
	
	public Page<BookResponseDTO> pageBooksV2(FiltersBookDTO filtersBookDTO) {
		return bookRepository.pageBooksV2(filtersBookDTO);
	}
	
	public List<Book> livros(FiltersBookDTO filtersBookDTO) {
		Specification specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(StringUtils.hasText(filtersBookDTO.getIsbn())) {
			specs = specs.and(BookSpecification.isbnEqual(filtersBookDTO.getIsbn()));
		}
		
		return bookRepository.findAll(specs);
	}
}
