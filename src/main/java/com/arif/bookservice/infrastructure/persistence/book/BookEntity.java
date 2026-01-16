package com.arif.bookservice.infrastructure.persistence.book;

import com.arif.bookservice.domain.book.Book;
import com.arif.bookservice.domain.book.BookId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String title;
    private String author;
    private String isbn;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    private String description;

    protected BookEntity() {}

    static BookEntity fromDomain(Book book) {
        BookEntity e = new BookEntity();
        e.id = book.getId().value();
        e.title = book.getTitle();
        e.author = book.getAuthor();
        e.isbn = book.getIsbn();
        e.publicationYear = book.getPublicationYear();
        e.categoryId = book.getCategoryId();
        e.description = book.getDescription();
        return e;
    }

    Book toDomain() {
        return new Book(
                new BookId(id),
                title,
                author,
                isbn,
                publicationYear,
                categoryId,
                description
        );
    }
}
