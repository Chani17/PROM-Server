package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.model.notice.DateResponse;
import inu.thebite.toryaba.model.notice.NoticesDatesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByStudentIdAndYearAndMonthAndDate(Long studentId, String year, int month, String date);

    @Query("SELECT new inu.thebite.toryaba.model.notice.DateResponse(n.year, n.month, n.date, n.day) FROM Notice n WHERE n.student.id = :studentId AND n.year = :year AND n.month = :month")
    List<DateResponse> findByStudentIdAndYearAndMonthAndDate(Long studentId, @Param("year") String year, @Param("month") int month);

    @Query("SELECT new inu.thebite.toryaba.model.notice.NoticesDatesResponse(n.year, n.month) FROM Notice n WHERE n.student.id = :studentId")
    List<NoticesDatesResponse> findYearAndMonthByStudentId(Long studentId);
}
