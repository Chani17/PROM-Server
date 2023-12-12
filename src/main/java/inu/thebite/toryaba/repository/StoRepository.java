package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.model.sto.StoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoRepository extends JpaRepository<Sto, Long> {

    @Query("SELECT new inu.thebite.toryaba.model.sto.StoResponse(s.id, s.templateNum, s.status, s.name, s.contents, s.count, s.goal, s.urgeType, s.urgeContent, s.enforceContent, s.memo, s.round, s.hitGoalDate, s.registerDate, s.delYN, s.lto.id) FROM Sto s WHERE s.lto.id = :ltoId")
    List<StoResponse> findAllByLtoIdWithStoResponse(@Param("ltoId") Long ltoId);

    List<Sto> findAllByLtoId(Long ltoId);
}

