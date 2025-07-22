package com.livro.api.livroapi.book.service;

import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.dto.author.AuthorDTO;
import com.livro.api.livroapi.exception.ConflictException;
import com.livro.api.livroapi.repository.AuthorRepository;
import com.livro.api.livroapi.service.AuthorService;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
	
	@InjectMocks
	private AuthorService authorService;
	
	@Mock
	private AuthorRepository authorRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	void salvar() {
		Author author = getAuthor();
		AuthorDTO authorDTO = getAuthorDTO();
		when(modelMapper.map(authorDTO, Author.class)).thenReturn(author);
		when(authorRepository.save(any(Author.class))).thenReturn(author);
		String uuId = authorService.save(authorDTO);
		verify(authorRepository, times(1)).save(any(Author.class));
		Assertions.assertNotNull(uuId);
	}
	
	@Test
	void naoDevesalvar() {
		AuthorDTO authorDTO = getAuthorDTO();
		when(authorRepository.existsByNameIgnoreCaseAndDateBirthAndNationalityIgnoreCase(
			authorDTO.name(), 
			authorDTO.dateBirth(), 
			authorDTO.nationality())
		).thenReturn(Boolean.TRUE);
		
		ConflictException exception = 
			assertThrows(ConflictException.class,() -> authorService.save(authorDTO));
		assertEquals("Esse registro já existe na base de dados!", exception.getMessage());
	}
	
	@Test
	void update() {
		UUID uuid = UUID.randomUUID();
		when(authorRepository.findById(uuid)).thenReturn(optionalAuthor());
		Author authorAtual =  authorRepository.findById(uuid).get();
		AuthorDTO authorDTO = getAuthorDTO();
		when(authorRepository.save(any(Author.class))).thenReturn(authorAtual);
		authorService.update(uuid.toString(), authorDTO);
		verify(authorRepository, times(1)).save(authorAtual);
		Assertions.assertNotNull(authorAtual.getId());
	}
	
	private AuthorDTO getAuthorDTO() {
		var authorDTO = AuthorDTO.of("C.S Lwis", LocalDate.now(), "USA");
		return authorDTO;
	}
	
	private Author getAuthor() {
		Author author = new Author(UUID.randomUUID(), "Nome do autor", LocalDate.now(), "USA");
		return author;
	}
	
	private Optional<Author> optionalAuthor() {
		return Optional.of(new Author(UUID.randomUUID(), "Olavo de Carvalho", LocalDate.now(), "BRA"));
	}
	
}
