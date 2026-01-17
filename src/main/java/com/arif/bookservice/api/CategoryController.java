package com.arif.bookservice.api;

import com.arif.bookservice.api.dto.UpdateCategoryRequest;
import com.arif.bookservice.application.category.command.CreateCategoryCommand;
import com.arif.bookservice.application.category.command.CategoryHandler;
import com.arif.bookservice.application.category.command.UpdateCategoryCommand;
import com.arif.bookservice.application.category.query.CategoryQueryHandler;
import com.arif.bookservice.application.category.query.CategoryView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Category query API")
public class CategoryController {
    private final CategoryQueryHandler queryHandler;
    private final CategoryHandler commandHandler;
    public CategoryController(CategoryQueryHandler queryHandler, CategoryHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
    }


    @Operation(summary = "Create new Category")
    @ApiResponse(responseCode = "201", description = "Category created")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createCategory(@RequestBody CreateCategoryCommand command) {
        return this.commandHandler.Handle(command);
    }

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<CategoryView> getAllCategories() {
        return this.queryHandler.Handle();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {

        this.commandHandler.HandleUpdate(
                new UpdateCategoryCommand(
                        id,
                        request.getName(),
                        request.getDescription()
                )
        );
    }

    @GetMapping("/{id}")
    public CategoryView getCategory(@PathVariable UUID id) {
        return this.queryHandler.HandleById(id);
    }

}
