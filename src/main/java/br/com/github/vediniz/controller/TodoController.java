package br.com.github.vediniz.controller;

import br.com.github.vediniz.entity.Todo;
import br.com.github.vediniz.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody(required = false) @Valid Todo todo) {
        if (todo == null) {
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }

        if (todo.getName() == null || todo.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Name cannot be empty")
            );
        }

        if (todo.getDescription() == null || todo.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Description cannot be empty")
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return ResponseEntity.ok(todoService.list());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Todo todo) {
        if (todo == null) {
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }
        return ResponseEntity.ok(todoService.update(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(todoService.delete(id));
    }
}