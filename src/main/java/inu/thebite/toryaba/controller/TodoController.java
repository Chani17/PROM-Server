package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.todo.AddTodoList;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/{studentId}")
    public ResponseEntity addTodoList(@PathVariable Long studentId, @RequestBody AddTodoList addTodoList) {
        todoService.addTodoList(studentId, addTodoList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{studentId}")
    public ResponseEntity updateTodoList(@PathVariable Long studentId, @RequestBody UpdateTodoList updateTodoList) {
        todoService.updateTodoList(studentId, updateTodoList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
