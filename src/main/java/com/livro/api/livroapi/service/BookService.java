package com.livro.api.livroapi.service;

import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.repository.BookRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Transactional
	public String salvar(Book book) {
		return bookRepository.save(book).getId().toString();
	}
	
	@Transactional
	public void update(String uuid, Book book) {
		Book bookAtual = bookRepository.findById(UUID.fromString(uuid)).get();
		BeanUtils.copyProperties(book, bookAtual);
		bookRepository.save(bookAtual);
	}
	
	@Transactional
	public void delete(String uuid) {
		bookRepository.deleteById(UUID.fromString(uuid));
	}
	
	public Book getBook(String uuid) {
		return bookRepository.getReferenceById(UUID.fromString(uuid));
	}
	
	public List<Book> getAll(String orderBy) {
		if(orderBy == null) orderBy = "id";
		return bookRepository.findAll(Sort.by(orderBy));
	}
}
