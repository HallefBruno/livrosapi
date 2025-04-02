package com.livro.api.livroapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String isbn;
    
    private String name;
    
    private String title;
    
    private LocalDate datePublish;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    private BigDecimal price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(LocalDate datePublish) {
        this.datePublish = datePublish;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
	
	@PrePersist
	@PreUpdate
	void validBasicEntity() {
		if(this.name == null||this.name.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
		} else if (this.title == null || this.title.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
		}
	}
}
