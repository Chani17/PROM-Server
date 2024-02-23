package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Lto;
import inu.thebite.toryaba.model.lto.LtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LtoRepository extends JpaRepository<Lto, Long> {

//    @Query("SELECT new inu.thebite.toryaba.model.lto.LtoResponse(l.id, l.templateNum, l.status, l.name, l.contents, l.developType, l.achieveDate, l.registerDate, l.delYN, l.domain.id, l.student.id) FROM Lto l WHERE l.student.id = :studentId AND l.domain.id = :domainId")
    List<Lto> findAllByStudentIdAndDomainId(Long studentId, Long domainId);

//    @Query("SELECT new inu.thebite.toryaba.model.lto.LtoResponse(l.id, l.templateNum, l.status, l.name, l.contents, l.developType, l.achieveDate, l.registerDate, l.delYN, l.domain.id, l.student.id) FROM Lto l WHERE l.student.id = :studentId")
    List<Lto> findAllByStudentId(Long studentId);

    @Query(value = "SELECT * FROM tb_lto WHERE lto_seq = :stoId", nativeQuery = true)
    Optional<Lto> findByStoId(@Param("stoId") Long stoId);
}