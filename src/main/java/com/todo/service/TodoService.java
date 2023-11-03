package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.exception.ResourceNotFoundException;

import java.util.List;

public interface TodoService {

    // save  task

    public TodoDto addTodo(TodoDto dto);

    public TodoDto getById(int id) throws ResourceNotFoundException;

    public List<TodoDto> getAll();

    public TodoDto update(TodoDto dto,int id) throws  ResourceNotFoundException;

    public void deleteTodo(int id) throws  ResourceNotFoundException;

    public TodoDto isComplete(int id) throws  ResourceNotFoundException;

    public TodoDto isNotComplete(int id) throws ResourceNotFoundException;

}
