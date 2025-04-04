package com.livro.api.livroapi.book.service;

import com.livro.api.livroapi.model.Genero;
import com.livro.api.livroapi.dto.BookDTO;
import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.repository.BookRepository;
import com.livro.api.livroapi.service.BookService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	@BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
    void save() {
		Book book = getBook();
        when(bookRepository.save(any(Book.class))).thenReturn(book);
		BookDTO bookDTO = getBookDTO();
        String savedBook = bookService.salvar(bookDTO);
        verify(bookRepository, times(1)).save(any(Book.class));
        assertNotNull(savedBook);
    }
	
	private BookDTO getBookDTO() {
		return new BookDTO("isbn", "name", "title", LocalDate.now(), Genero.DRAMATICO, BigDecimal.TEN);
	}
	
	private Book getBook() {
		Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("Mockito for Beginners");
		book.setDatePublish(LocalDate.now());
		book.setIsbn("isbn");
		book.setPrice(BigDecimal.ONE);
		return book;
	}
}
