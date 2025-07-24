package com.livro.api.livroapi.dto.book;

import com.livro.api.livroapi.dto.FiltrosDefault;
import com.livro.api.livroapi.model.Genero;

public class FiltersBookDTO extends FiltrosDefault {
	
	private String isbn;
	private String title;
	private String nameAuthor;
	private Genero genero;
	private Integer yearPublication;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public Integer getYearPublication() {
		return yearPublication;
	}

	public void setYearPublication(Integer yearPublication) {
		this.yearPublication = yearPublication;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}
