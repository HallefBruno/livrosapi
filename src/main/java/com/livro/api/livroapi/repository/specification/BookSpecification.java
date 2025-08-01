package com.livro.api.livroapi.repository.specification;

import com.livro.api.livroapi.model.Book;
import com.livro.api.livroapi.model.Genero;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
	
	public static Specification<Book> isbnEqual(String isbn) {
		return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
	}
	
	public static Specification<Book> titleLike(String title) {
		return (root, query, cb) -> cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
	}
	
	public static Specification<Book> generoEqual(Genero genero) {
		return (root, query, cb) -> cb.equal(root.get("title"), genero);
	}
	
}
