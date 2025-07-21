package com.livro.api.livroapi.controller;

import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.dto.AuthorDTO;
import com.livro.api.livroapi.dto.FiltrosAuthor;
import com.livro.api.livroapi.service.AuthorService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/author")
public class AuthorControllerV1 {
    
    private AuthorService authorService;
    
    public AuthorControllerV1(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Author> save(@RequestBody(required = true) @Valid AuthorDTO author) {
        String uuid = authorService.save(author);
        URI toUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(uuid).toUri();
        return ResponseEntity.created(toUri).build();
    }
    
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthor(@PathVariable(name = "uuid", required = true) String uuid) {
        return authorService.getAuthor(uuid);
    }
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{uuid}")
	public void update(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody(required = true) @Valid AuthorDTO authorDTO) {
		authorService.update(uuid, authorDTO);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable(name = "uuid", required = true) String uuid) {
		if(authorService.delete(uuid)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Page<Author> getAllAuthors(@RequestBody(required = true) FiltrosAuthor filtrosAuthor) {
        return authorService.getAll(filtrosAuthor);
    }
	
	@GetMapping("/all/v2")
	@ResponseStatus(HttpStatus.OK)
	public List<Author> getAllAuthorsv2(@RequestBody(required = false) FiltrosAuthor filtrosAuthor) {
		return authorService.exampleAllAuthors(filtrosAuthor);
	}
    
}
