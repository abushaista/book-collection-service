package com.arif.bookservice.infrastructure.persistence.category;

import com.arif.bookservice.domain.category.Category;
import com.arif.bookservice.domain.category.CategoryId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, unique = false, length = 1000)
    private String description;

    protected CategoryEntity() {}
    public CategoryEntity(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    static CategoryEntity fromDomain(Category category) {
        CategoryEntity e = new CategoryEntity();
        e.id = category.getId().value();
        e.name = category.getName();
        e.description = category.getDescription();
        return e;
    }

    Category toDomain() {
        return new Category(
                new CategoryId(id),
                name,
                description
        );
    }
}
