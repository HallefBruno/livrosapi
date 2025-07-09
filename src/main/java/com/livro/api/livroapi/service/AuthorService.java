package com.livro.api.livroapi.service;

import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.dto.AuthorDTO;
import com.livro.api.livroapi.dto.FiltrosAuthor;
import com.livro.api.livroapi.exception.ConflictException;
import com.livro.api.livroapi.repository.AuthorRepository;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorService {
    
    private AuthorRepository authorRepository;
	private ModelMapper modelMapper;
    
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
		this.modelMapper = modelMapper;
    }

    @Transactional
    public String save(AuthorDTO authorDTO) {
		if(authorRepository.existsByNameIgnoreCaseAndDateBirthAndNationalityIgnoreCase(authorDTO.name(), authorDTO.dateBirth(), authorDTO.nationality())) {
			throw new ConflictException("Esse registro já existe na base de dados!");
		}
		Author author = modelMapper.map(authorDTO, Author.class);
        return authorRepository.save(author).getId().toString();
    }
    
    public Author getAuthor(String uuid) {
		return authorRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> 
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum resultado encontrado para o identificador: "+uuid));
    }
	
	@Transactional
	public void update(String uuid, AuthorDTO authorDTO) {
		Author authorAtual = getAuthor(uuid);
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
