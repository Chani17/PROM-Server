package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/{studentId}")
    public ResponseEntity addTodoList(@PathVariable Long studentId, @RequestBody TodoListRequest todoListRequest) {
        todoService.addTodoList(studentId, todoListRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{studentId}")
    public ResponseEntity updateTodoList(@PathVariable Long studentId, @RequestBody UpdateTodoList updateTodoList) {
        todoService.updateTodoList(studentId, updateTodoList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 해당 로직이 TodoList 수정하는 로직과 동일하여 없어도 될 듯함.
    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteTodoList(@PathVariable Long studentId, @RequestBody UpdateTodoList updateTodoList) {
        todoService.deleteTodoList(studentId, updateTodoList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{studentId}")
    public List<String> getTodoList(@PathVariable Long studentId) {
        List<String> todoList = todoService.getTodoList(studentId);
        return todoList;
    }
}
