package com.example.todolist.controller;

import com.example.todolist.entity.TodoItem;
import com.example.todolist.exception.ItemsNotFound;
import com.example.todolist.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping("/todos")
    public ResponseEntity<TodoItem> createItem(@Valid @RequestBody TodoItem item) {
        repository.save(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoItem>> findAll(){
        List<TodoItem> items = repository.findAll();
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    /*@GetMapping("/todos/{todosId}")
    public TodoItem findById(@PathVariable Long todosId){

        Optional<TodoItem> optionalUser = repository.findById(todosId);

        return optionalUser.orElse(null);
    }*/

    @GetMapping("/todos/{todosId}")
    public ResponseEntity<TodoItem> findById(@PathVariable Long todosId) {

        Optional<TodoItem> todoItem = repository.findById(todosId);

        if (todoItem.isEmpty()) {
            throw new ItemsNotFound("Item not found with id: " + todosId);
        }

        TodoItem item = todoItem.orElse(null);

        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @PutMapping("/todos")
    public ResponseEntity<String> update(@Valid @RequestBody TodoItem item) {

        repository.save(item);

        String message = "Uptated successfully";

        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/todos/{todosId}")
    public ResponseEntity<String> delete(@PathVariable Long todosId) {
        repository.deleteById(todosId);

        String message = todosId + " was successfully deleted";

        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
