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
    Optional<Todo> findByDateAndStudentId(String date, Long id);

    @Modifying
    @Query(value = "DELETE FROM todo_sto_list WHERE sto_list = :stoId", nativeQuery = true)
    void deleteByStoId(@Param("stoId") Long stoId);

    List<Todo> findByStudentIdBetweenDate(Long studentId, LocalDate startDate, LocalDate endDate);
}
