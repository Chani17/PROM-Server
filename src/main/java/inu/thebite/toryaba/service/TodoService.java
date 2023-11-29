package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;

import java.util.List;

public interface TodoService {
    void addTodoList(Long studentId, TodoListRequest todoListRequest);

    Todo createTodoList(Student student);

    void updateTodoList(Long studentId, UpdateTodoList updateTodoList);

    void deleteTodoList(Long studentId, UpdateTodoList updateTodoList);

    List<String> getTodoList(Long studentId);
}
