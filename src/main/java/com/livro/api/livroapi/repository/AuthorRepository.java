package com.livro.api.livroapi.repository;

import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.repository.querys.author.AuthorRepositoryCustom;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>, AuthorRepositoryCustom {
    
	boolean existsByNameIgnoreCaseAndDateBirthAndNationalityIgnoreCase(String name, LocalDate dateBirth, String nationality);
	
}
