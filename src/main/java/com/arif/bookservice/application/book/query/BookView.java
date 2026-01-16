package com.arif.bookservice.application.book.query;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BookView{
    private final UUID id;
    private final String title;
    private final String author;
    private final String isbn;
    private final Integer publicationYear;
    private final UUID categoryId;
    private final String categoryName;
    private final String description;
    public BookView(
            UUID id,
            String title,
            String author,
            String isbn,
            Integer publicationYear,
            UUID categoryId,
            String categoryName,
            String description
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }
}
