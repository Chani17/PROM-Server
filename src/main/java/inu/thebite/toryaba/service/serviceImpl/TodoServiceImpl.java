package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.entity.Todo;
import inu.thebite.toryaba.model.sto.StoSummaryResponse;
import inu.thebite.toryaba.model.todo.RecentTodoWithDateResponse;
import inu.thebite.toryaba.model.todo.TodoListRequest;
import inu.thebite.toryaba.model.todo.UpdateTodoList;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.repository.TodoRepository;
import inu.thebite.toryaba.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public Todo addTodoList(User user, Long studentId, TodoListRequest todoListRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo findTodo = todoRepository.findByDateAndStudentId(LocalDate.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId())
                .orElse(null);

        // 기존에 생성한 TodoList가 존재한다면 sto 추가
        Sto sto = stoRepository.findById(todoListRequest.getStoId())
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        // 기존에 생성한 TodoList가 존재하지 않다면 새롭게 생성
        if(findTodo == null) {
            Todo todoList = createTodoList(user.getUsername(), student);
            todoList.addTodoList(sto.getId());

            // TodoList에 항목이 추가될 때 Notice도 함께 생성
            Notice notice = Notice.createNotice(student);
            noticeRepository.save(notice);
            return todoList;

        } else {
            findTodo.addTodoList(sto.getId());
            return findTodo;
        }

    }

    @Transactional
    @Override
    public Todo createTodoList(String teacher, Student student) {
        Todo todo = Todo.createTodo(student, teacher);
        todoRepository.save(todo);
        return todo;
    }

    @Transactional
    @Override
    public Todo updateTodoList(User user, Long studentId, UpdateTodoList updateTodoList) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudentId(LocalDate.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.updateTodoList(updateTodoList.getStoList(), user.getUsername());
        return todo;
    }

    @Override
    public void deleteTodoList(User user, Long studentId, UpdateTodoList updateTodoList) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudentId(LocalDate.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        todo.updateTodoList(updateTodoList.getStoList(), user.getUsername());

    }

    @Override
    public StoSummaryResponse getTodoList(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        Todo todo = todoRepository.findByDateAndStudentId(LocalDate.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), DateTimeFormatter.ofPattern("yyyy/MM/dd")), student.getId()).
                orElseThrow(() -> new IllegalStateException("해당 Todo List가 존재하지 않습니다."));

        StoSummaryResponse response = StoSummaryResponse.response(todo.getId(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), todo.getStoList(), student);
        return response;

//        for(Long stoId : todo.getStoList()) {
//            Sto sto = stoRepository.findById(stoId)
//                    .orElseThrow(() -> new IllegalStateException("해당 STO가 존재하지 않습니다."));
//
//            StoSummaryResponse response = StoSummaryResponse.response(sto.getId(), sto.getName(), sto.getStatus(), sto.getLto());
//            stoList.add(response);
    }

    @Override
    public List<RecentTodoWithDateResponse> getRecentTodoListWithDate(Long studentId, String startDate, String endDate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        List<Todo> result = todoRepository.findByStudentIdBetween(student.getId(), LocalDate.parse(startDate), LocalDate.parse(endDate));
        List<RecentTodoWithDateResponse> recentTodo = new ArrayList<>();

        for(Todo todo : result) {
            List<String> stoName = new ArrayList<>();
            for(Long sto : todo.getStoList()) {
                Sto findSto = stoRepository.findById(sto)
                        .orElseThrow(() -> new IllegalStateException("존재하지 않는 STO 입니다."));
                stoName.add(findSto.getName());
            }
            recentTodo.add(RecentTodoWithDateResponse.response(todo.getDate(), stoName, todo.getTeacher()));
        }
        return recentTodo;
    }

    @Override
    public List<RecentTodoWithDateResponse> getRecentTodoListWithFilterBetweenDate(Long studentId, String filter, String startDate, String endDate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생 아이디 입니다. 확인해주세요."));

        List<Todo> result = todoRepository.findByStudentIdBetween(student.getId(), LocalDate.parse(startDate), LocalDate.parse(endDate));
        List<RecentTodoWithDateResponse> response = new ArrayList<>();

        for(Todo todo : result) {
            for()
        }
    }
}
