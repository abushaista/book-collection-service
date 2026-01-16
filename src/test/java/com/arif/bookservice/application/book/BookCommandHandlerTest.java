package com.arif.bookservice.application.book;
import com.arif.bookservice.application.book.command.*;
import com.arif.bookservice.application.category.CategoryNotFoundException;
import com.arif.bookservice.domain.book.Book;
import com.arif.bookservice.domain.book.BookId;
import com.arif.bookservice.infrastructure.persistence.book.JpaBookRepository;
import com.arif.bookservice.infrastructure.query.category.JdbcCategoryQueryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookCommandHandlerTest {

    @Test
    void update_should_throw_exception_when_not_found(){
        JpaBookRepository jpaBookRepository = Mockito.mock(JpaBookRepository.class);
        JdbcCategoryQueryRepository jdbcCategoryQueryRepository = Mockito.mock(JdbcCategoryQueryRepository.class);
        BookCommandHandler handler = new BookCommandHandler(jpaBookRepository, jdbcCategoryQueryRepository);
        UUID bookId = UUID.randomUUID();

        when(jpaBookRepository.findById(new BookId(bookId))).thenReturn(Optional.empty());
        UpdateBookCommand cmd = new UpdateBookCommand(
                bookId,
                "Clean Architecture",
                "Robert C. Martin",
                "9780134494166",
                2017,
                UUID.randomUUID(),
                "DDD book"
        );
        BookNotFoundException ex =
                assertThrows(BookNotFoundException.class,
                        () -> handler.update(cmd));
        assertEquals(ex.getMessage(), "Book not found: " + bookId);
        verify(jpaBookRepository, never()).save(any(Book.class));
    }

    @Test
    void insert_should_throw_exception_when_category_not_found(){
        JpaBookRepository jpaBookRepository = Mockito.mock(JpaBookRepository.class);
        JdbcCategoryQueryRepository jdbcCategoryQueryRepository = Mockito.mock(JdbcCategoryQueryRepository.class);
        BookCommandHandler handler = new BookCommandHandler(jpaBookRepository, jdbcCategoryQueryRepository);
        UUID categoryId = UUID.randomUUID();

        when(jdbcCategoryQueryRepository.existsById(categoryId)).thenReturn(false);
        CreateBookCommand cmd = new CreateBookCommand(
                "Clean Architecture",
                "Robert C. Martin",
                "9780134494166",
                2017,
                categoryId,
                "DDD book"
        );
        CategoryNotFoundException ex = assertThrows(CategoryNotFoundException.class,
                () -> handler.create(cmd));
        assertEquals(ex.getMessage(), "Category not found: " + categoryId);
        verify(jdbcCategoryQueryRepository, only()).existsById(categoryId);

    }

}


