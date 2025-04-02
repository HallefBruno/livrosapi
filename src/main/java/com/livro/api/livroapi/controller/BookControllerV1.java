package com.livro.api.livroapi.controller;

import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.service.BookService;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/book")
public class BookControllerV1 {
	
	private BookService bookService;
	
	public BookControllerV1(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping
	public ResponseEntity<Book> salvar(Book book) {
		String uuid = bookService.salvar(book);
		URI toUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(uuid).toUri();
        return ResponseEntity.created(toUri).build();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{uuid}")
	public void update(@PathVariable(name = "uuid", required = true) String uuid, Book book) {
		bookService.update(uuid, book);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	public void delete(@PathVariable(name = "uuid", required = true) String uuid) {
		bookService.delete(uuid);
	}
	
	@GetMapping("/{uuid}")
	public Book getBook(@PathVariable(name = "uuid", required = true) String uuid) {
		return ResponseEntity.ok(bookService.getBook(uuid)).getBody();
	}
	
	@GetMapping("/{oderBy}")
	public List<Book> allBooks(@RequestParam(name = "orderBy", required = false) String orderBy) {
		return ResponseEntity.ok(bookService.getAll(orderBy)).getBody();
	}
	
}
