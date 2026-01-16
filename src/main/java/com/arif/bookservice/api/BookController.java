package com.arif.bookservice.api;

import com.arif.bookservice.api.dto.CreateBookRequest;
import com.arif.bookservice.application.book.command.CreateBookCommand;
import com.arif.bookservice.application.book.command.BookCommandHandler;
import com.arif.bookservice.application.book.command.DeleteBookCommand;
import com.arif.bookservice.application.book.command.UpdateBookCommand;
import com.arif.bookservice.application.book.query.BookQueryHandler;
import com.arif.bookservice.application.book.query.BookView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book management API")
public class BookController {
    private final BookCommandHandler commandHandler;
    private final BookQueryHandler bookQueryHandler;

    public BookController(BookCommandHandler commandHandler, BookQueryHandler bookQueryHandler) {
        this.commandHandler = commandHandler;
        this.bookQueryHandler = bookQueryHandler;
    }

    @Operation(summary = "Create new book")
    @ApiResponse(responseCode = "201", description = "Book created")
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

    @Operation(summary = "Get book by id",
    description = "Get specific book by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public BookView get(@PathVariable UUID id) {
        return bookQueryHandler.getBook(id);
    }

    @Operation(summary = "Get all books",
            description = "List books with optional search by title or author")
    @GetMapping
    public List<BookView> getAll(@RequestParam(required = false) String search) {
        return bookQueryHandler.getBooks(search);
    }

    @Operation(summary = "Update existing book")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
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

    @Operation(summary = "Delete book by ID")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        commandHandler.delete(new DeleteBookCommand(id));
    }

}
