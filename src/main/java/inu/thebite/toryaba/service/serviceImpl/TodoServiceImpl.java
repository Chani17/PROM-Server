package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.repository.TodoRepository;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final NoticeRepository noticeRepository;


    @Transactional
    @Override
    public void addTodoList(Long studentId, TodoListRequest todoListRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo findTodo = todoRepository.findByDateAndStudentId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId())
                .orElse(null);

        // 기존에 생성한 TodoList가 존재하지 않다면 새롭게 생성
        if(findTodo == null) {
            Todo todo = Todo.createTodo(student);
            todoRepository.save(todo);
        }

        // 이 부분 분리 필요 Transactional 벆애서 잔행 팔요
        // 기존에 생성한 TodoList가 존재한다면 sto 추가
        Sto sto = stoRepository.findById(todoListRequest.getStoId())
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        findTodo.addTodoList(sto.getId());

        // TodoList에 항목이 추가될 때 Notice도 함께 생성
        Notice notice = Notice.createNotice(student);
        noticeRepository.save(notice);
    }

    @Transactional
    @Override
    public void updateTodoList(Long studentId, UpdateTodoList updateTodoList) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudentId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.updateTodoList(updateTodoList.getStoList());
    }

    @Override
    public void deleteTodoList(Long studentId, UpdateTodoList updateTodoList) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudentId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.updateTodoList(updateTodoList.getStoList());

    }

    @Override
    public List<Sto> getTodoList(Long studentId) {

        List<Sto> stoList = new ArrayList<>();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudentId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        for(Long stoId : todo.getStoList()) {
            Sto sto = stoRepository.findById(stoId)
                    .orElseThrow(() -> new IllegalStateException("해당 STO가 존재하지 않습니다."));
            stoList.add(sto);
        }
        return stoList;
    }
}
