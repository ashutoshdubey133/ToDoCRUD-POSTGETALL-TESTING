package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoServices {

    private final ToDoRepository toDoRepository;


    public TodoServices(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public void addTodo(Todo todo){
        toDoRepository.save(todo);
    }

    public List<Todo> getAllTodo() {
        return toDoRepository.findAll();
    }
}
