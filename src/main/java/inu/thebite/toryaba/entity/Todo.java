package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_todo")
public class Todo extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;
    
    @Column(name = "todo_date")
    private String date;

    @ElementCollection
    @Column(name = "sto_list", nullable = false)
    private List<Long> stoList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_seq")
    private Student student;

    // Todo 생성할 때는 batch로 만들어야 할 것을 고려해야함.
    public static Todo createTodo(Student student) {
        Todo todo = new Todo();
        todo.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        todo.student = student;
        return todo;
    }

    public void addTodoList(Long stoId) {
        this.stoList.add(stoId);
    }

    public void updateTodoList(List<Long> stoList) {
        this.stoList = stoList;
    }

}
