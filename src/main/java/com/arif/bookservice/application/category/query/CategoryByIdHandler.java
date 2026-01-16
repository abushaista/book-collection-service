package com.arif.bookservice.application.category.query;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryByIdHandler {
    private final CategoryQueryRepository repository;
    public CategoryByIdHandler(CategoryQueryRepository repository) {
        this.repository = repository;
    }
    public CategoryView Handle(UUID id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Category not found: " + id
                        )
                );
    }
}
