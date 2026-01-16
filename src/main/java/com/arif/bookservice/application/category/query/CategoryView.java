package com.arif.bookservice.application.category.query;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoryView {
    private final UUID id;
    private final String name;
    private final String description;
    public CategoryView(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
