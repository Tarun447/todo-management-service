package com.todo.controller;


import com.todo.dto.TodoDto;
import com.todo.exception.ResourceNotFoundException;
import com.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Todo Management Controller",
description = "Todo Management Rest Api Config ")

public class TodoRestController {

    private TodoService service;

    @Autowired
    public TodoRestController(TodoService service) {
        this.service = service;
    }


    @PostMapping("/todo")
    @Operation(method = "POST",description = "Save Todo",summary = "Save Todo")
    @ApiResponse(responseCode = "201",description = "Success Code 201 ",useReturnTypeSchema = true)
    public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto dto)
    {

        try {
            return new ResponseEntity<TodoDto>(service.addTodo(dto), HttpStatus.CREATED);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @GetMapping("/todo/{id}")
    @Operation(method = "GET",summary = "Get Todo Data By Id",description = "Get Todo Data By Id")
    @ApiResponse(responseCode = "200",description = "Success code 200")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") int id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(service.getById(id));
        }
        catch(ResourceNotFoundException rs) {
            throw rs;
        }
    }


    @GetMapping("/todo")
    @Operation(method = "GET",summary = "Get All Todo Data ",description = "Get All Todo Data")
    @ApiResponse(responseCode = "200",description = "Success code 200")
    public ResponseEntity<List<TodoDto>> getAlltodo()
    {
        return ResponseEntity.ok(service.getAll());
    }


    @PutMapping("/todo/{id}")
    @Operation(method = "PUT",summary = "Update Todo Data By id",description = "Update Todo Data By id")
    @ApiResponse(responseCode = "200",description = "Success code 200")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto dto,@PathVariable("id") int id) throws ResourceNotFoundException
    {
        return ResponseEntity.ok(service.update(dto,id));
    }



    @DeleteMapping("todo/{id}")
    @Operation(method = "DELETE",summary = "Delete Todo Data By id",description = "Deletes Todo Data By id")
    @ApiResponse(responseCode = "200",description = "Success code 200")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted with id : "+id);
    }

    @PatchMapping("/{id}/complete")
    @Operation(method = "PATCH",summary = "update Complete status  Mark as Complete Todo Data By id",description = "update Complete status  Mark as Complete Todo Data By id")
    @ApiResponse(responseCode = "200",description = "Success code 200")
    public ResponseEntity<TodoDto> isComplete(@PathVariable("id") int id) throws ResourceNotFoundException
    {
       return  ResponseEntity.ok(service.isComplete(id));
    }

    @PatchMapping("/{id}/notcomplete")
    @Operation(method = "PATCH",summary = "update Complete status Mark as inComplete Todo Data By id",description = "update Complete status Mark as inComplete Todo Data By id")
    @ApiResponse(responseCode = "200",description = "Success code 200")
    public ResponseEntity<TodoDto> isNotComplete(@PathVariable("id") int id) throws ResourceNotFoundException
    {
        return  ResponseEntity.ok(service.isNotComplete(id));
    }


}
