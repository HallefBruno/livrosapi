package com.livro.api.livroapi.controller;

import com.livro.api.livroapi.dto.book.BookDTO;
import com.livro.api.livroapi.dto.book.BookResponseDTO;
import com.livro.api.livroapi.dto.book.FiltersBookDTO;
import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.service.BookService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/book")
public class BookControllerV1 implements GenericController {
	
	private BookService bookService;
	
	public BookControllerV1(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping
	public ResponseEntity<Book> salvar(@RequestBody(required = true) @Valid BookDTO book) {
		String uuid = bookService.salvar(book);
		URI toUri = gerarHeaderLocaltion(uuid);
        return ResponseEntity.created(toUri).build();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{uuid}")
	public void update(@PathVariable(name = "uuid", required = true) String uuid, @Valid BookDTO book) {
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
	
	@GetMapping("/all-books")
	public Page<BookResponseDTO> allBooks(@RequestBody FiltersBookDTO filtersBookDTO) {
		return ResponseEntity.ok(bookService.pageBooks(filtersBookDTO)).getBody();
	}
	
	@GetMapping("/v2/all-books")
	public Page<BookResponseDTO> allBooksV2(@RequestBody FiltersBookDTO filtersBookDTO) {
		return ResponseEntity.ok(bookService.pageBooksV2(filtersBookDTO)).getBody();
	}
	
}
