package com.arif.bookservice.infrastructure.persistence.category;

import com.arif.bookservice.domain.category.Category;
import com.arif.bookservice.domain.category.CategoryId;
import com.arif.bookservice.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaCategoryRepository implements CategoryRepository {
    private final SpringDataCategoryRepository jpa;
    public JpaCategoryRepository(SpringDataCategoryRepository jpa) {
        this.jpa = jpa;
    }
    @Override
    public void save(Category category) {
        this.jpa.save(CategoryEntity.fromDomain(category));
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return Optional.empty();
    }
}
