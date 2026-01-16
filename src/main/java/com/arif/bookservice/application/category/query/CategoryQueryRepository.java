package com.arif.bookservice.application.category.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryQueryRepository {
    List<CategoryView> findAll();
    Optional<CategoryView> findById(UUID id);
}
