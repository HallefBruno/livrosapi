package com.livro.api.livroapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tb_author")
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    
    private LocalDate dateBirth;
    
    private String nationality;
	
	@CreatedDate
	private LocalDateTime dateRegisterSystem;
	
	@LastModifiedDate
	private LocalDateTime lastUpdateDate;
	
	private UUID userId;
	
	@JsonManagedReference
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books = new ArrayList<>();

	public Author(UUID id, String name, LocalDate dateBirth, String nationality, List<Book> books) {
		this.id = id;
		this.name = name;
		this.dateBirth = dateBirth;
		this.nationality = nationality;
		this.books = books;
	}

	public Author(UUID id, String name, LocalDate dateBirth, String nationality) {
		this.id = id;
		this.name = name;
		this.dateBirth = dateBirth;
		this.nationality = nationality;
	}

	public Author(String name, LocalDate dateBirth, String nationality) {
		this.name = name;
		this.dateBirth = dateBirth;
		this.nationality = nationality;
	}

	public Author() {
	}
	

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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
