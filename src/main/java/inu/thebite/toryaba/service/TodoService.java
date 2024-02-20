package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.sto.StoSummaryResponse;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import org.springframework.security.core.userdetails.User;


public interface TodoService {
    Todo addTodoList(User user, Long studentId, TodoListRequest todoListRequest);

    Todo createTodoList(String teacher, Student student);

    Todo updateTodoList(User user, Long studentId, UpdateTodoList updateTodoList);

    void deleteTodoList(User user, Long studentId, UpdateTodoList updateTodoList);

    StoSummaryResponse getTodoList(Long studentId);
}
