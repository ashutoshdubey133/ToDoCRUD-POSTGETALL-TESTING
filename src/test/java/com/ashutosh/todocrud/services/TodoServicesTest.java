package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.repository.ToDoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing TodoServicesTest")
class TodoServicesTest {

    @Mock
    private ToDoRepository toDoRepository;
    private TodoServices todoServices;

    private Todo todo;

    @BeforeEach
    void setup(){
        todoServices = new TodoServices(toDoRepository);
    }

    @Test
    @DisplayName("Testing Success of Adding Todo")
    void addTodoSuccess() {
        todo = new Todo();
        todo.setId(1L);
        todo.setContent("test Todo");
        todoServices.addTodo(todo);
        verify(toDoRepository,times(1).description("You did not make a call to the todo Repository")).save(todo);
    }

    @Test
    @DisplayName("Testing Success of Getting All Todos")
    void getAllTodoSuccess(){
        Assertions.assertEquals(new ArrayList<Todo>(),todoServices.getAllTodo(),"The Todo service is not returning list of Todos");
        verify(toDoRepository,times(1).description("You did not make a call to the todo Repository")).findAll();
    }

}