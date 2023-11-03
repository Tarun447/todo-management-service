package com.todo.service.impl;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.exception.ResourceNotFoundException;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl  implements TodoService {

    private TodoRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    public TodoServiceImpl(TodoRepository todo)
    {
        this.repo = todo;
    }


    /**
     * @param dto
     * @return
     * @apiNote Save data into database
     */
    @Override
    public TodoDto addTodo(TodoDto dto) {
        Todo todo = mapper.map(dto,Todo.class);
        Todo td = repo.save(todo);
        TodoDto todoDto = mapper.map(td,TodoDto.class);
        return todoDto;
    }

    /**
     * @param id
     * @return
     * @apiNote Get data by id
     */
    @Override
    public TodoDto getById(int id) throws ResourceNotFoundException {
        Todo todo = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo is not fount with id : "+id));
        return mapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAll() {
        List<Todo> li = repo.findAll();

        // here i use java 8 feature stream api
        return li.stream().map((todo)->mapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto update(TodoDto dto, int id) throws ResourceNotFoundException {

        Todo todo = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo is not fount with id : "+id));
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.getCompleted());
        Todo updatedTodo = repo.save(todo);

        return mapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(int id) throws ResourceNotFoundException {
        Todo todo = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo is not fount with id : "+id));
        repo.deleteById(id);
    }

    @Override
    public TodoDto isComplete(int id) throws ResourceNotFoundException {
        Todo todo = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo is not fount with id : "+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo = repo.save(todo);
        return mapper.map(updateTodo,TodoDto.class);
    }

    @Override
    public TodoDto isNotComplete(int id) throws ResourceNotFoundException {
        Todo todo = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo is not fount with id : "+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updateTodo = repo.save(todo);
        return mapper.map(updateTodo,TodoDto.class);
    }


}

