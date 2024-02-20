package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.sto.StoSummaryResponse;
import inu.thebite.toryaba.model.todo.RecentTodoWithDateResponse;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/{studentId}")
    public Todo addTodoList(@LoginMember User user,
                            @PathVariable Long studentId,
                            @RequestBody TodoListRequest todoListRequest) {
        Todo todo = todoService.addTodoList(user, studentId, todoListRequest);
        return todo;
    }

    @PatchMapping(value = "/{studentId}")
    public Todo updateTodoList(@LoginMember User user,
                               @PathVariable Long studentId,
                               @RequestBody UpdateTodoList updateTodoList) {
        Todo todo = todoService.updateTodoList(user, studentId, updateTodoList);
        return todo;
    }

    // 해당 로직이 TodoList 수정하는 로직과 동일하여 없어도 될 듯함.
    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteTodoList(@LoginMember User user,
                                         @PathVariable Long studentId,
                                         @RequestBody UpdateTodoList updateTodoList) {
        todoService.deleteTodoList(user, studentId, updateTodoList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{studentId}")
    public StoSummaryResponse getTodoList(@PathVariable Long studentId) {
        StoSummaryResponse todoList = todoService.getTodoList(studentId);
        return todoList;
    }

    // recent todo list between startDate and endDate
    @GetMapping("/{studentId}/recent")
    public List<RecentTodoWithDateResponse> getRecentTodoListWithDate(@PathVariable Long studentId,
                                                                      @RequestParam String startDate,
                                                                      @RequestParam String endDate) {
        return todoService.getRecentTodoListWithDate(studentId, startDate, endDate);
    }
}
