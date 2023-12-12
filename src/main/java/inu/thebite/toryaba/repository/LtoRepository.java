package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Lto;
import inu.thebite.toryaba.model.lto.LtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LtoRepository extends JpaRepository<Lto, Long> {

    List<LtoResponse> findAllByDomainId(Long domainId);

    @Query("SELECT new inu.thebite.toryaba.model.lto.LtoResponse(l.id, l.templateNum, l.status, l.name, l.contents, l.game, l.achieveDate, l.registerDate, l.delYN, l.domain.id, l.student.id) FROM Lto l WHERE l.student.id = :studentId AND l.domain.id = :domainId")
    List<LtoResponse> findAllByStudentIdAndDomainId(Long studentId, Long domainId);

    @Query("SELECT new inu.thebite.toryaba.model.lto.LtoResponse(l.id, l.templateNum, l.status, l.name, l.contents, l.game, l.achieveDate, l.registerDate, l.delYN, l.domain.id, l.student.id) FROM Lto l WHERE l.student.id = :studentId")
    List<LtoResponse> findAllByStudentId(Long studentId);

}