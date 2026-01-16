package com.arif.bookservice.application.book.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookQueryRepository {
    List<BookView> findAll();

    List<BookView> searchByTitleOrAuthor(String keyword);

    Optional<BookView> findById(UUID id);
}
