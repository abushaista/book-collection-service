package com.arif.bookservice.domain.book;

import java.util.Optional;

public interface BookRepository {
    void save(Book book);
    Optional<Book> findById(BookId bookId);
    void delete(BookId bookId);
}
