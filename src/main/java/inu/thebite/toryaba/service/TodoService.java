package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;

public interface TodoService {
    void addTodoList(Long studentId, TodoListRequest todoListRequest);

    void updateTodoList(Long studentId, UpdateTodoList updateTodoList);

    void deleteTodoList(Long studentId, TodoListRequest todoListRequest);
}
