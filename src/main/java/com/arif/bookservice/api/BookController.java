package com.arif.bookservice.api;

import com.arif.bookservice.api.dto.CreateBookRequest;
import com.arif.bookservice.application.book.command.CreateBookCommand;
import com.arif.bookservice.application.book.command.BookCommandHandler;
import com.arif.bookservice.application.book.command.DeleteBookCommand;
import com.arif.bookservice.application.book.command.UpdateBookCommand;
import com.arif.bookservice.application.book.query.BookQueryHandler;
import com.arif.bookservice.application.book.query.BookView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookCommandHandler commandHandler;
    private final BookQueryHandler bookQueryHandler;

    public BookController(BookCommandHandler commandHandler, BookQueryHandler bookQueryHandler) {
        this.commandHandler = commandHandler;
        this.bookQueryHandler = bookQueryHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody CreateBookRequest request) {

        return commandHandler.create(
                new CreateBookCommand(
                        request.getTitle(),
                        request.getAuthor(),
                        request.getIsbn(),
                        request.getPublicationYear(),
                        request.getCategoryId(),
                        request.getDescription()
                )
        );
    }

    @GetMapping("/{id}")
    public BookView get(@PathVariable UUID id) {
        return bookQueryHandler.getBook(id);
    }

    @GetMapping
    public List<BookView> getAll(@RequestParam(required = false) String search) {
        return bookQueryHandler.getBooks(search);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody UpdateBookCommand body) {
        commandHandler.update(new UpdateBookCommand(
                id,
                body.title(),
                body.author(),
                body.isbn(),
                body.publicationYear(),
                body.categoryId(),
                body.description()
        ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        commandHandler.delete(new DeleteBookCommand(id));
    }

}
