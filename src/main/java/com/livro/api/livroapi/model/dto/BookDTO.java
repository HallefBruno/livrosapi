package com.livro.api.livroapi.model.dto;

import java.time.LocalDate;

public record BookDTO(String isbn, String name, String title, LocalDate datePublish, String genero) {

}
