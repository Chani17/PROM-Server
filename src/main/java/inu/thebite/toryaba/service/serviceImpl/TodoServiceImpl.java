package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.repository.StoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.repository.TodoRepository;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final StudentRepository studentRepository;
    private final StoRepository stoRepository;


    @Override
    public void addTodoList(Long studentId, TodoListRequest todoListRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudent(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        Sto sto = stoRepository.findById(todoListRequest.getStoId())
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        todo.addTodoList(sto.getId());
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
    public void deleteTodoList(Long studentId, UpdateTodoList updateTodoList) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudent(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.updateTodoList(updateTodoList.getStoList());

    }

    @Override
    public List<Sto> getTodoList(Long studentId) {

        List<Sto> stoList = new ArrayList<>();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudent(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        for(Long stoId : todo.getStoList()) {
            Sto sto = stoRepository.findById(stoId)
                    .orElseThrow(() -> new IllegalStateException("해당 STO가 존재하지 않습니다."));
            stoList.add(sto);
        }
        return stoList;
    }
}
