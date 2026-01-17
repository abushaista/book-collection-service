package com.arif.bookservice.application.category.query;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryQueryHandler {
    private final CategoryQueryRepository repository;
    public CategoryQueryHandler(CategoryQueryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryView> Handle(){
        return repository.findAll();
    }
    public CategoryView HandleById(UUID id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Category not found: " + id
                        )
                );
    }
}
