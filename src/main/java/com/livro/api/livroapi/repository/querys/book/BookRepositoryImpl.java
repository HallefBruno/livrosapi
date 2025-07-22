package com.livro.api.livroapi.repository.querys.book;

import com.livro.api.livroapi.dto.book.BookResponseDTO;
import com.livro.api.livroapi.dto.book.FiltersBookDTO;
import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.springframework.data.domain.Page;

public class BookRepositoryImpl implements BookRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<BookResponseDTO> pageBooks(FiltersBookDTO filtersBookDTO) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<Author> author = cq.from(Author.class);
		Join<Author, Book> book = author.join("books");
		List<Tuple> listTuple = em.createQuery(cq).getResultList();
		System.out.println(listTuple.get(0));
		return null;
	}

}
