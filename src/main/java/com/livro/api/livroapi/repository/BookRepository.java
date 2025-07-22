package com.livro.api.livroapi.repository;

import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.repository.querys.book.BookRepositoryCustom;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, BookRepositoryCustom {

}
