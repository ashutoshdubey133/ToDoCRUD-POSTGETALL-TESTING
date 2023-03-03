package com.ashutosh.todocrud.controller;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.repository.ToDoRepository;
import com.ashutosh.todocrud.services.TodoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoServices todoServices;

    @MockBean
    private ToDoRepository toDoRepository;

    private Todo todo;

    @Test
    void addTodo() throws Exception {
        todo = new Todo("testing todo");
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJSON = objectMapper.writeValueAsString(todo);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/todo").contentType(MediaType.APPLICATION_JSON).content(todoJSON)).andExpect(status().isOk());
    }

    @Test
    void getAllTodos() throws Exception {
        todo = new Todo("testing todo");
        List<Todo> list = new ArrayList<>();
        list.add(todo);
        ObjectMapper objectMapper = new ObjectMapper();
        String listJSON = objectMapper.writeValueAsString(list);
        when(todoServices.getAllTodo()).thenReturn(list);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/todos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(listJSON));
    }

}