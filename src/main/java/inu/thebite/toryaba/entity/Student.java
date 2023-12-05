package inu.thebite.toryaba.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_student")
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_seq")
    private Long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_birth")
    private String birth;

    @Column(name = "student_etc")
    private String etc;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "student_start_dt")
    private String startDate;

    @Column(name = "student_end_dt")
    private String endDate;

    @Column(name = "student_reg_dt")
    private String registerDate;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_seq")
    private Class toryClass;


    public static Student createStudent(String name, String birth, String etc, String parentName, String startDate, Class toryClass) {
        Student student = new Student();
        student.name = name;
        student.birth = birth;
        student.etc = etc;
        student.parentName = parentName;
        student.startDate = startDate;
        student.registerDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        student.toryClass = toryClass;
        return student;
    }

    public void updateStudent(String name, String birth, String etc, String parentName, String startDate, String endDate, String registerDate) {
        this.name = name;
        this.birth = birth;
        this.etc = etc;
        this.parentName = parentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registerDate = registerDate;
    }

    public void updateStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void updateEndDate(String endDate) {
        this.endDate = endDate;
    }
}
