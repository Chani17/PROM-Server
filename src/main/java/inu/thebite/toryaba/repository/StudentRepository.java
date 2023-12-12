package inu.thebite.toryaba.repository;


import inu.thebite.toryaba.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByToryClassId(Long classId);
}
