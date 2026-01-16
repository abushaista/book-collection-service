package com.arif.bookservice.domain.book;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Book {
    private final BookId id;
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private UUID categoryId;
    private String description;

    public  Book(BookId id, String title, String author, String isbn, Integer publicationYear, UUID categoryId, String description) {
        if (id == null) throw new IllegalArgumentException("BookId is required");
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("CategoryId is required");
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.description = description;
        this.categoryId = categoryId;
    }

    public void Update(
            String title, String author, String isbn, Integer publicationYear, UUID categoryId, String description
    ){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.description = description;
        this.categoryId = categoryId;
    }

    public void changeTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public void changeCategory(UUID newCategoryId) {
        if (newCategoryId == null) {
            throw new IllegalArgumentException("CategoryId is required");
        }
        this.categoryId = newCategoryId;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

}
