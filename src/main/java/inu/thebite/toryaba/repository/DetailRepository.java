package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.model.notice.DetailCommentAndStoIdResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Optional<Detail> findByNoticeIdAndStoId(Long noticeId, Long stoId);

//    @Query("SELECT new inu.thebite.toryaba.model.notice.DetailGraphResponse(d.id, d.stoId, d.notice.id) FROM Detail d WHERE d.notice.id = :noticeId")
//    List<DetailGraphResponse> findAllByNoticeId(Long noticeId);

    boolean existsByStoIdAndYearAndMonthAndDate(Long stoId, String year, int month, String date);

    void deleteByStoId(Long stoId);

    boolean existsByLtoIdAndYearAndMonthAndDate(Long id, String year, int month, String date);

    Detail findByLtoIdAndYearAndMonthAndDate(Long id, String year, int month, String date);

    List<Detail> findByNoticeId(Long noticeId);
}
