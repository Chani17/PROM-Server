package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.todo.AddTodoList;
import inu.thebite.toryaba.model.todo.UpdateTodoList;

public interface TodoService {
    void addTodoList(Long studentId, AddTodoList addTodoList);

    void updateTodoList(Long studentId, UpdateTodoList updateTodoList);
}
