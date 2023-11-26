package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.AddTodoList;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/{studentId}")
    public ResponseEntity addTodoList(@PathVariable Long studentId, @RequestBody AddTodoList addTodoList) {
        todoService.addTodoList(studentId, addTodoList)

    }
}
