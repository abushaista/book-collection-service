package com.arif.bookservice.application.book;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID id) {
        super("Book not found: " + id);
    }
}
