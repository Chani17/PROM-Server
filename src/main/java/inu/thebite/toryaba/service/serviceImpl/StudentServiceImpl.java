package inu.thebite.toryaba.service.serviceImpl;


import inu.thebite.toryaba.entity.Class;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.student.AddStudentRequest;
import inu.thebite.toryaba.model.student.UpdateStudentDateRequest;
import inu.thebite.toryaba.model.student.UpdateStudentRequest;
import inu.thebite.toryaba.repository.ClassRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    @Transactional
    @Override
    public Student addStudent(Long classId, AddStudentRequest addStudentRequest) {
        Class findClass = classRepository.findById(classId)
                .orElseThrow(() -> new IllegalStateException("해당 반은 존재하지 않습니다."));

        Student student = Student.createStudent(addStudentRequest.getName(), addStudentRequest.getBirth(), addStudentRequest.getEtc(), addStudentRequest.getParentName(), addStudentRequest.getStartDate(), findClass);
        studentRepository.save(student);
        return student;
    }

    @Transactional
    @Override
    public Student updateStudent(Long studentId, UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당 아이의 대한 정보가 존재하지 않습니다."));

        student.updateStudent(updateStudentRequest.getName(), updateStudentRequest.getBirth(),
                updateStudentRequest.getEtc(), updateStudentRequest.getParentName(),
                updateStudentRequest.getStartDate(), updateStudentRequest.getEndDate(),
                updateStudentRequest.getRegisterDate());
        return student;
    }

    @Transactional
    @Override
    public Student updateStudentStartDate(Long studentId, UpdateStudentDateRequest updateStudentDateRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당 아이의 대한 정보가 존재하지 않습니다."));

        student.updateStartDate(updateStudentDateRequest.getDate());
        return student;
    }

    @Transactional
    @Override
    public Student updateStudentEndDate(Long studentId, UpdateStudentDateRequest updateStudentDateRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당 아이의 대한 정보가 존재하지 않습니다."));

        student.updateEndDate(updateStudentDateRequest.getDate());
        return student;
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @Override
    public void deleteStudent(Long studentId) {
        if(!studentRepository.findById(studentId).isPresent()) {
            throw new IllegalStateException("해당 아이의 대한 정보가 존재하지 않습니다.");
        }
        studentRepository.deleteById(studentId);
    }
}
