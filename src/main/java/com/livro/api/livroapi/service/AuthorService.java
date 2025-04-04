package com.livro.api.livroapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.dto.AuthorDTO;
import com.livro.api.livroapi.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorService {
    
    private AuthorRepository authorRepository;
    
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public String save(AuthorDTO authorDTO) {
		Author author = new Author();
		BeanUtils.copyProperties(authorDTO, author);
        return authorRepository.save(author).getId().toString();
    }
    
    public Author getAuthor(String uuid) {
		return authorRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> 
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum resultado encontrado para o identificador: "+uuid));
    }
	
	@Transactional
	public void update(String uuid, Author author) {
		Author authorAtual = authorRepository.findById(UUID.fromString(uuid)).get();
		BeanUtils.copyProperties(author, authorAtual, "id");
		authorRepository.save(authorAtual);
	}
	
	@Transactional
	public void delete(String uuid) {
		authorRepository.deleteById(UUID.fromString(uuid));
	}
	
	public List<Author> getAll(String orderBy) {
		if(orderBy == null) orderBy = "id";
		return authorRepository.findAll(Sort.by(orderBy));
	}
}
