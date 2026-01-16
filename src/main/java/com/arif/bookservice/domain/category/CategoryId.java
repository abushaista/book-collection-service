package com.arif.bookservice.domain.category;

import java.util.UUID;

public record CategoryId(UUID value) {
    public static CategoryId newId() {
        return new CategoryId(UUID.randomUUID());
    }
}
