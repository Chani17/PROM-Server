package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.student.AddStudentRequest;
import inu.thebite.toryaba.model.student.UpdateStudentDateRequest;
import inu.thebite.toryaba.model.student.UpdateStudentRequest;

import java.util.List;

public interface StudentService {
    Student addStudent(Long classId, AddStudentRequest addStudentRequest);

    Student updateStudentStartDate(Long studentId, UpdateStudentDateRequest updateStudentDateRequest);

    Student updateStudentEndDate(Long studentId, UpdateStudentDateRequest updateStudentDateRequest);

    void deleteStudent(Long studentId);

    List<Student> getStudentList(Long classId);

    Student updateStudent(Long studentId, UpdateStudentRequest updateStudentRequest);
}
