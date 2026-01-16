package com.arif.bookservice.domain.category;

import java.util.Optional;

public interface CategoryRepository {
    void save(Category category);
    Optional<Category> findById(CategoryId id);
}
