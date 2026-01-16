package com.arif.bookservice.application.book.query;

import com.arif.bookservice.application.book.BookNotFoundException;
import com.arif.bookservice.domain.book.BookId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookQueryHandler {
    private final BookQueryRepository repository;

    public BookQueryHandler(BookQueryRepository repository) {
        this.repository = repository;
    }

    public List<BookView> getBooks(String search) {
        if (search == null || search.isBlank()) {
            return repository.findAll();
        }
        return repository.searchByTitleOrAuthor(search);
    }

    public BookView getBook(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );
    }
}
