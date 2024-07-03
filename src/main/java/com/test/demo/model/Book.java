package com.test.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.persistence.Column;

@Entity
@Table(name = "book") // Optional: If table name is different from class name
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Author is required")
    @Column(name = "author")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:?\\s*)?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})" +
            "[- 0-9]{17}$)\\d{1,5}[- ]\\d{1,7}[- ]\\d{1,6}[- ]\\d$",
            message = "Invalid ISBN format")
    @Column(name = "isbn")
    private String isbn;

    @NotNull(message = "Publication year is required")
    @PositiveOrZero(message = "Publication year must be a positive number or zero")
    @Column(name = "publicationYear")
    private Integer publicationYear;
    

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setpublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public int getpublicationYear() {
        return publicationYear != null ? publicationYear.intValue() : 0; // Return 0 or another default value if publicationYear is null
    }
    // Other methods as needed
}