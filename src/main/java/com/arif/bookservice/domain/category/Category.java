package com.arif.bookservice.domain.category;

import lombok.Getter;

@Getter
public class Category {
    private final CategoryId id;
    private final String name;
    private final String description;
    public Category(CategoryId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
