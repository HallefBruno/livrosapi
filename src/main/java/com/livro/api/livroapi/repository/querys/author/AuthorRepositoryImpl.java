package com.livro.api.livroapi.repository.querys.author;

import com.livro.api.livroapi.dto.FiltrosAuthor;
import com.livro.api.livroapi.model.Author;
import com.livro.api.livroapi.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Page<Author> pageAuthor(FiltrosAuthor filtrosAuthor) {
		
		int paginaAtual = filtrosAuthor.getPageNumber();
		int totalRegistrosPorPagina = filtrosAuthor.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		Map<String, Object> map = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(a) from Author a left join a.books b where 1=1 ");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> query = cb.createQuery(Author.class);
		Root<Author> author = query.from(Author.class);
		Join<Author, Book> book = (Join) author.fetch("books");
		List<Predicate> predicates = new ArrayList<>();
		
		if(filtrosAuthorIsValid(filtrosAuthor) && Objects.nonNull(filtrosAuthor.getName())) {
			predicates.add( cb.like(cb.upper(author.get("name")), "%" + filtrosAuthor.getName().toUpperCase()+ "%") );
			sql.append(" and upper(a.name) like upper(concat('%', :name ,'%'))  ");
			map.put("name", filtrosAuthor.getName());
		}
		
		if(filtrosAuthorIsValid(filtrosAuthor) && Objects.nonNull(filtrosAuthor.getBookName())) {
			predicates.add( cb.like(cb.upper(book.get("name")), "%" + filtrosAuthor.getBookName()+ "%" ) );
			sql.append(" and upper(b.name) like upper(concat('%', :bookName ,'%'))  ");
			map.put("bookName", filtrosAuthor.getBookName());
		}
		
		query.select(author);
		query.where(predicates.toArray(Predicate[]::new));
		if(filtrosAuthor.getDirection().equalsIgnoreCase("asc")) {
			query.orderBy(cb.asc(author.get(filtrosAuthor.getOrderBy())));
		} else {
			query.orderBy(cb.desc(author.get(filtrosAuthor.getOrderBy())));
		}
		TypedQuery<Author> typedQuery = em.createQuery(query);
		typedQuery.setFirstResult(primeiroRegistro);
		typedQuery.setMaxResults(totalRegistrosPorPagina);
		
		TypedQuery<Long> queryCount = em.createQuery(sql.toString(), Long.class);
		map.forEach(queryCount::setParameter);
		
		return new PageImpl<>(typedQuery.getResultList(), PageRequest.of(paginaAtual, totalRegistrosPorPagina), queryCount.getSingleResult());
	}
	
	private boolean filtrosAuthorIsValid(FiltrosAuthor filtrosAuthor) {
		return filtrosAuthor != null;
	}
	
}
 