package com.arif.bookservice.domain.category;

import lombok.Getter;

@Getter
public class Category {
    private final CategoryId id;
    private String name;
    private String description;
    public Category(CategoryId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void Update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
