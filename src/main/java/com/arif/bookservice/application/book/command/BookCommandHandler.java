package com.arif.bookservice.application.book.command;

import com.arif.bookservice.application.book.BookNotFoundException;
import com.arif.bookservice.domain.book.Book;
import com.arif.bookservice.domain.book.BookId;
import com.arif.bookservice.domain.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookCommandHandler {
    private final BookRepository repository;
    public BookCommandHandler(BookRepository repository) {
        this.repository = repository;
    }

    public UUID create(CreateBookCommand cmd) {

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
