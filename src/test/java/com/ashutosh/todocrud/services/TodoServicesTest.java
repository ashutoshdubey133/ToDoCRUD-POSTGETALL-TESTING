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

import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TodoServices.class})
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing TodoServicesTest")
class TodoServicesTest {

    @Mock
    private ToDoRepository toDoRepository;
    private TodoServices todoServices;

    private Todo todo;

    @BeforeEach
    void setup() {
        todoServices = new TodoServices(toDoRepository);
    }

    /**
     * Method under test: {@link TodoServices#addTodo(Todo)}
     */
    @Test
    void testAddTodo() {
        when(toDoRepository.save((Todo) any())).thenReturn(new Todo("Not all who wander are lost"));
        Todo todo = new Todo("Not all who wander are lost");
        todoServices.addTodo(todo);
        verify(toDoRepository,description("You did not make a call to the todo Repository")).save((Todo) any());
        assertFalse(todo.getCompleted());
        assertEquals(0L, todo.getId());
        assertEquals("Not all who wander are lost", todo.getContent());
    }

    /**
     * Method under test: {@link TodoServices#getAllTodo()}
     */
    @Test
    void testGetAllTodo() {
        ArrayList<Todo> todoList = new ArrayList<>();
        when(toDoRepository.findAll()).thenReturn(todoList);
        List<Todo> actualAllTodo = todoServices.getAllTodo();
        assertSame(todoList, actualAllTodo,"The Todo service is not returning required list of Todos");
        assertTrue(actualAllTodo.isEmpty(), "The Todo service is not returning required list of Todos");
        verify(toDoRepository, description("You did not make a call to the todo Repository")).findAll();
    }

}