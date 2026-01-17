package com.arif.bookservice.application.category.command;

import com.arif.bookservice.application.category.CategoryNotFoundException;
import com.arif.bookservice.domain.category.Category;
import com.arif.bookservice.domain.category.CategoryId;
import com.arif.bookservice.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryHandler {
    private final CategoryRepository categoryRepository;
    public CategoryHandler(CategoryRepository categoryRepository) {
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

    public void HandleUpdate(UpdateCategoryCommand updateCategoryCommand) {
        Category category = this.categoryRepository.
                findById(new CategoryId(updateCategoryCommand.id()))
                .orElseThrow(() -> new CategoryNotFoundException(updateCategoryCommand.id()));
        category.Update(updateCategoryCommand.name(), updateCategoryCommand.description());
        categoryRepository.save(category);
    }
}
