package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.student.AddStudentRequest;
import inu.thebite.toryaba.model.student.UpdateStudentDateRequest;
import inu.thebite.toryaba.model.student.UpdateStudentRequest;
import inu.thebite.toryaba.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // add student
    @PostMapping("/{classId}/students")
    public Student addStudent(@LoginMember User user, @PathVariable Long classId, @RequestBody AddStudentRequest addStudentRequest) {
        String userId = user.getUsername();
        Student student = studentService.addStudent(userId, classId, addStudentRequest);
        return student;
    }

    // update student info
    @PatchMapping("/students/{studentId}")
    public Student updateStudent(@LoginMember User user, @PathVariable Long studentId, @RequestBody UpdateStudentRequest updateStudentRequest) {
        Student student = studentService.updateStudent(studentId, updateStudentRequest);
        return student;
    }

    // update startDate
    @PatchMapping("/students/{studentId}/startDate")
    public Student updateStudentStartDate(@LoginMember User user, @PathVariable Long studentId,
                                          @RequestBody UpdateStudentDateRequest updateStudentDateRequest) {
        Student student = studentService.updateStudentStartDate(studentId, updateStudentDateRequest);
        return student;
    }

    // update endDate
    @PatchMapping("/students/{studentId}/endDate")
    public Student updateStudentEndDate(@LoginMember User user, @PathVariable Long studentId,
                                        @RequestBody UpdateStudentDateRequest updateStudentDateRequest) {
        Student student = studentService.updateStudentEndDate(studentId, updateStudentDateRequest);
        return student;
    }

    // get student list
    @GetMapping("/{classId}/students")
    public List<Student> getStudentList(@LoginMember User user, @PathVariable Long classId) {
        List<Student> studentList = studentService.getStudentList(classId);
        return studentList;
    }

    // delete student
    @DeleteMapping("/students/{studentId}")
    public boolean deleteStudent(@LoginMember User user, @PathVariable Long studentId) {
        boolean result = studentService.deleteStudent(studentId);
        return result;
    }
}
