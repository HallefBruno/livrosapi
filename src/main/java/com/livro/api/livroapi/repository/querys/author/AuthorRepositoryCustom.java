
package com.livro.api.livroapi.repository.querys.author;

import com.livro.api.livroapi.dto.FiltrosAuthor;
import com.livro.api.livroapi.model.Author;
import org.springframework.data.domain.Page;

public interface AuthorRepositoryCustom {
	
	Page<Author> pageAuthor(FiltrosAuthor filtrosAuthor);
	
}
