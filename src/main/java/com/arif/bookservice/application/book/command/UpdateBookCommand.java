package com.arif.bookservice.application.book.command;

import java.util.UUID;

public record UpdateBookCommand(
        UUID id,
        String title,
        String author,
        String isbn,
        Integer publicationYear,
        UUID categoryId,
        String description
) {
}
