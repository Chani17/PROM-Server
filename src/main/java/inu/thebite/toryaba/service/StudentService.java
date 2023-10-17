package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.student.AddStudentRequest;
import inu.thebite.toryaba.model.student.UpdateStudentDateRequest;

import java.util.List;

public interface StudentService {
    Student addStudent(Long classId, AddStudentRequest addStudentRequest);

    Student updateStudentStartDate(Long classId, Long studentId, UpdateStudentDateRequest updateStudentDateRequest);

    Student updateStudentEndDate(Long classId, Long studentId, UpdateStudentDateRequest updateStudentDateRequest);

    void deleteStudent(Long classId, Long studentId);

    List<Student> getStudentList();
}
