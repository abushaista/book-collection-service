package com.arif.bookservice.application.category.command;

public record CreateCategoryCommand(
        String name,
        String description
) {
}
