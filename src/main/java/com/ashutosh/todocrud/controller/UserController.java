package com.ashutosh.todocrud.controller;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.services.TodoServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final TodoServices todoServices;

    public UserController(TodoServices todoServices){
        this.todoServices = todoServices;
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo){
        System.out.println(todo);
        todoServices.addTodo(todo);
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoServices.getAllTodo();
    }
}
