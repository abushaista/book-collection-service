package com.arif.bookservice.infrastructure.persistence.book;

import com.arif.bookservice.domain.book.Book;
import com.arif.bookservice.domain.book.BookId;
import com.arif.bookservice.domain.book.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaBookRepository implements BookRepository {

    private final SpringDataBookRepository jpa;

    public JpaBookRepository(SpringDataBookRepository jpa) {
        this.jpa = jpa;
    }
    @Override
    public void save(Book book) {
        jpa.save(BookEntity.fromDomain(book));
    }

    @Override
    public Optional<Book> findById(BookId bookId) {
        return jpa.findById(bookId.value())
                .map(BookEntity::toDomain);
    }

    @Override
    public void delete(BookId bookId) {
        jpa.deleteById(bookId.value());
    }

}
