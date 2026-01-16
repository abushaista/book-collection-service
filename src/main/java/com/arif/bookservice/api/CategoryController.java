package com.arif.bookservice.api;

import com.arif.bookservice.application.category.command.CreateCategoryCommand;
import com.arif.bookservice.application.category.command.CreateCategoryHandler;
import com.arif.bookservice.application.category.query.CategoryQueryHandler;
import com.arif.bookservice.application.category.query.CategoryView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryQueryHandler queryHandler;
    private final CreateCategoryHandler commandHandler;
    public CategoryController(CategoryQueryHandler queryHandler, CreateCategoryHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public UUID createCategory(@RequestBody CreateCategoryCommand command) {
        return this.commandHandler.Handle(command);
    }

    @GetMapping
    public List<CategoryView> getAllCategories() {
        return this.queryHandler.Handle();
    }
}
