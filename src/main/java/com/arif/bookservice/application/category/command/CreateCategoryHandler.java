package com.arif.bookservice.application.category.command;

import com.arif.bookservice.domain.category.Category;
import com.arif.bookservice.domain.category.CategoryId;
import com.arif.bookservice.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCategoryHandler {
    private final CategoryRepository categoryRepository;
    public CreateCategoryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public UUID Handle(CreateCategoryCommand createCategoryCommand) {
        Category category = new Category(
                CategoryId.newId(),
                createCategoryCommand.name(),
                createCategoryCommand.description()
        );
        categoryRepository.save(category);
        return category.getId().value();
    }
}
