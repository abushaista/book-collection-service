package com.arif.bookservice.application.category.command;

import java.util.UUID;

public record UpdateCategoryCommand(
        UUID id,
        String name,
        String description
) {
}
