package com.arif.bookservice.application.book.command;

import com.arif.bookservice.application.book.BookNotFoundException;
import com.arif.bookservice.application.category.CategoryNotFoundException;
import com.arif.bookservice.application.category.query.CategoryQueryRepository;
import com.arif.bookservice.domain.book.Book;
import com.arif.bookservice.domain.book.BookId;
import com.arif.bookservice.domain.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookCommandHandler {
    private final BookRepository repository;
    private final CategoryQueryRepository categoryRepository;
    public BookCommandHandler(BookRepository repository, CategoryQueryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public UUID create(CreateBookCommand cmd) {
        if (!categoryRepository.existsById(cmd.categoryId())) {
            throw new CategoryNotFoundException(cmd.categoryId());
        }
        Book book = new Book(
                BookId.newId(),
                cmd.title(),
                cmd.author(),
                cmd.isbn(),
                cmd.publicationYear(),
                cmd.categoryId(),
                cmd.description()
        );

        repository.save(book);
        return book.getId().value();
    }


    public void update(UpdateBookCommand cmd) {
        Book book = repository.findById(new BookId(cmd.id())).orElseThrow(
                () -> new BookNotFoundException(cmd.id())
        );
        if (!categoryRepository.existsById(cmd.categoryId())) {
            throw new CategoryNotFoundException(cmd.categoryId());
        }
        book.Update(
                cmd.title(),
                cmd.author(),
                cmd.isbn(),
                cmd.publicationYear(),
                cmd.categoryId(),
                cmd.description()
        );
        repository.save(book);
    }

    public void delete(DeleteBookCommand cmd) {
        this.repository.delete(new BookId(cmd.id()));
    }
}
