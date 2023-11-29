package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByDateAndStudentId(String format, Long id);
}
