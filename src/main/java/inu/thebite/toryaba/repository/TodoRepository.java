package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByDateAndStudentId(LocalDate date, Long id);

    @Modifying
    @Query(value = "DELETE FROM todo_sto_list WHERE sto_list = :stoId", nativeQuery = true)
    void deleteByStoId(@Param("stoId") Long stoId);

    @Query("SELECT t FROM Todo t WHERE t.student.id = :studentId AND t.date BETWEEN :startDate AND :endDate")
    List<Todo> findByStudentIdBetween(@Param("studentId") Long studentId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
