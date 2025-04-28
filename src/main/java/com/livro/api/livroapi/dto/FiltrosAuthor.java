package com.livro.api.livroapi.dto;


public class FiltrosAuthor extends FiltrosDefault {
	
	private String name;
	private String bookName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
