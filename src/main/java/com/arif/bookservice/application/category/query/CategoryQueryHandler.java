package com.arif.bookservice.application.category.query;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQueryHandler {
    private final CategoryQueryRepository repository;
    public CategoryQueryHandler(CategoryQueryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryView> Handle(){
        return repository.findAll();
    }
}
