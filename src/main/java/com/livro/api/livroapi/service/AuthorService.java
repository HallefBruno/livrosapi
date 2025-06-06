package com.livro.api.livroapi.service;

import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.dto.AuthorDTO;
import com.livro.api.livroapi.dto.FiltrosAuthor;
import com.livro.api.livroapi.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
	public void update(String uuid, AuthorDTO authorDTO) {
		Author authorAtual = authorRepository.findById(UUID.fromString(uuid)).get();
		BeanUtils.copyProperties(authorDTO, authorAtual, "id");
		authorRepository.save(authorAtual);
	}
	
	@Transactional
	public boolean delete(String uuid) {
		Optional<Author> author = authorRepository.findById(UUID.fromString(uuid));
		if(author.isPresent()) {
			authorRepository.deleteById(UUID.fromString(uuid));
			return true;
		}
		return false;
	}
	
	public Page<Author> getAll(FiltrosAuthor filtrosAuthor) {
		return authorRepository.pageAuthor(filtrosAuthor);
	}
}
