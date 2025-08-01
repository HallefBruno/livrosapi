package com.livro.api.livroapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tb_book")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String isbn;
    
    private String name;
    
    private String title;
    
	@Future(message = "Publication date must be in the future")
    private LocalDate datePublish;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    private BigDecimal price;
	
	@JsonBackReference
	@JoinColumn(name = "author_id")
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	private Author author;
	
	@CreatedDate
	private LocalDateTime dateRegisterSystem;
	
	@LastModifiedDate
	private LocalDateTime lastUpdateDate;
	
	private UUID userId;

	public Book(UUID id, String isbn, String name, String title, LocalDate datePublish, Genero genero, BigDecimal price) {
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.title = title;
		this.datePublish = datePublish;
		this.genero = genero;
		this.price = price;
	}

	public Book() {
	}
	
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public LocalDateTime getDateRegisterSystem() {
		return dateRegisterSystem;
	}

	public void setDateRegisterSystem(LocalDateTime dateRegisterSystem) {
		this.dateRegisterSystem = dateRegisterSystem;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
}