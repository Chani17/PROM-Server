package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.model.notice.DetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Optional<Detail> findByNoticeId(Long id);

    @Query("SELECT new inu.thebite.toryaba.model.notice.DetailResponse(d.id, d.comment, d.stoId, d.notice.id) FROM Detail d WHERE d.notice.id = :noticeId")
    List<DetailResponse> findAllByNoticeId(Long noticeId);
}
