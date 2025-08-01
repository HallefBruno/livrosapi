package com.livro.api.livroapi.repository.querys.book;

import com.livro.api.livroapi.dto.book.BookResponseDTO;
import com.livro.api.livroapi.dto.book.FiltersBookDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

public class BookRepositoryImpl implements BookRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<BookResponseDTO> pageBooks(FiltersBookDTO filtersBookDTO) {
		
		int paginaAtual = filtersBookDTO.getPageNumber();
		int totalRegistrosPorPagina = filtersBookDTO.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		StringBuilder sqlAnd = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		
		sql.append(" select new com.livro.api.livroapi.dto.book.BookResponseDTO(b.id, b.isbn, b.name, b.title, b.datePublish, b.genero, b.price, new com.livro.api.livroapi.dto.book.AuthorResponseDTO(a.name, a.dateBirth, a.nationality)) from Book b join b.author a where 1=1 ");
		sqlCount.append(" select count(*) from Book b join b.author a where 1=1 ");
		
		if(StringUtils.hasText(filtersBookDTO.getIsbn())) {
			sqlAnd.append(" and b.isbn = :isbn ");
			params.put("isbn", filtersBookDTO.getIsbn());
		}
		
		if(StringUtils.hasText(filtersBookDTO.getTitle())) {
			sqlAnd.append(" and upper(b.title) like upper(concat('%', :title ,'%')) ");
			params.put("title", filtersBookDTO.getTitle());
		}
		
		if(StringUtils.hasText(filtersBookDTO.getNameAuthor())) {
			sqlAnd.append(" and upper(a.name) like upper(concat('%', :nameAuthor ,'%')) ");
			params.put("nameAuthor", filtersBookDTO.getNameAuthor());
		}
		
		if(Objects.nonNull(filtersBookDTO.getGenero())) {
			sqlAnd.append(" and b.genero = :genero ");
			params.put("genero", filtersBookDTO.getGenero());
		}
		
		if(Objects.nonNull(filtersBookDTO.getYearPublication())) {
			sqlAnd.append(" and year(b.datePublish) = :yearPublication ");
			params.put("YearPublication", filtersBookDTO.getYearPublication());
		}
		
		sql.append(sqlAnd);
		sqlCount.append(sqlAnd);
		
		TypedQuery<BookResponseDTO> query = em.createQuery(sql.toString(), BookResponseDTO.class);
		
		params.forEach(query::setParameter);
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
		
		TypedQuery<Long> queryCount = em.createQuery(sqlCount.toString(), Long.class);
		params.forEach(queryCount::setParameter);
		
		return new PageImpl<>(query.getResultList(), PageRequest.of(paginaAtual, totalRegistrosPorPagina), queryCount.getSingleResult());
	}
	
	@Override
	public Page<BookResponseDTO> pageBooksV2(FiltersBookDTO filtersBookDTO) {
		int paginaAtual = filtersBookDTO.getPageNumber();
		int totalRegistrosPorPagina = filtersBookDTO.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select new com.livro.api.livroapi.dto.book.BookResponseDTO(b.id, b.isbn, b.name, b.title, b.datePublish, b.genero, b.price, new com.livro.api.livroapi.dto.book.AuthorResponseDTO(a.name, a.dateBirth, a.nationality)) ");
		jpql.append(" from Book b ");
		jpql.append(" join b.author a ");
		jpql.append(" where (:isbn is null or b.isbn = :isbn) ");
		jpql.append(" and (:title is null or upper(b.title) like upper(cast(:title as string))) ");
		jpql.append(" and (:nameAuthor is null or upper(a.name) like upper(cast(:nameAuthor as string))) ");
		jpql.append(" and (:genero is null or b.genero = :genero) ");
		jpql.append(" and (:yearPublication is null or year(b.datePublish) = :yearPublication) ");

		TypedQuery<BookResponseDTO> query = em.createQuery(jpql.toString(), BookResponseDTO.class);
		
		params.put("isbn", filtersBookDTO.getIsbn());
		params.put("title", filtersBookDTO.getTitle());
		params.put("nameAuthor", filtersBookDTO.getNameAuthor());
		params.put("genero", filtersBookDTO.getGenero());
		params.put("yearPublication", filtersBookDTO.getYearPublication());
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
		params.forEach(query::setParameter);
		
		List<BookResponseDTO> dtos = query.getResultList();
		
		StringBuilder countJpql = new StringBuilder();
		countJpql.append(" select count(b) ");
		countJpql.append(" from Book b ");
		countJpql.append(" join b.author a ");
		countJpql.append(" where (:isbn is null or b.isbn = :isbn) ");
		countJpql.append(" and (:title is null or upper(b.title) like upper(cast(:title as string))) ");
		countJpql.append(" and (:nameAuthor is null or upper(a.name) like upper(cast(:nameAuthor as string))) ");
		countJpql.append(" and (:genero is null or upper(b.genero) like upper(cast(:genero as string))) ");
		countJpql.append(" and (:yearPublication is null or year(b.datePublish) = :yearPublication) ");

		TypedQuery<Long> countQuery = em.createQuery(countJpql.toString(), Long.class);
		params.forEach(countQuery::setParameter);
		
		Long total = countQuery.getSingleResult();

		return new PageImpl<>(dtos, PageRequest.of(paginaAtual, totalRegistrosPorPagina), total);
	}

}
