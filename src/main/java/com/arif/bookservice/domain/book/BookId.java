package com.arif.bookservice.domain.book;

import java.util.UUID;

public record BookId(UUID value) {
    public static BookId newId() {
        return new BookId(UUID.randomUUID());
    }
}
