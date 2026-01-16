package com.arif.bookservice.infrastructure.persistence.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBookRepository extends JpaRepository<BookEntity, UUID> {
}
