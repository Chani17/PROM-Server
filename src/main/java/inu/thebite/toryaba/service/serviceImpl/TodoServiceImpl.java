package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.repository.TodoRepository;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final StudentRepository studentRepository;

    @Override
    public void addTodoList(Long studentId, TodoListRequest todoListRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudent(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.addTodoList(todoListRequest.getStoId());
    }

    @Override
    public void updateTodoList(Long studentId, UpdateTodoList updateTodoList) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudent(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.updateTodoList(updateTodoList.getStoList());
    }

    @Override
    public void deleteTodoList(Long studentId, TodoListRequest todoListRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudent(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        // 어떻게 삭제할건데?
    }
}
