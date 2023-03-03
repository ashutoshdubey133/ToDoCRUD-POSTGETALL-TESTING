package com.ashutosh.todocrud;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.repository.ToDoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoCrudApplication implements CommandLineRunner {

    private final ToDoRepository toDoRepository;

    public ToDoCrudApplication(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ToDoCrudApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Todo todo = new Todo();
        todo.setContent("Coding Ninjas Assignment");

        toDoRepository.save(todo);
    }
}
