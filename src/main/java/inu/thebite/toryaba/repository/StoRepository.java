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

    @Query("SELECT s FROM Sto s WHERE s.lto.id = :ltoId")
    List<Sto> findByLtoIdWithStoResponse(@Param("ltoId") Long ltoId);


    @Query("SELECT s.looseCannonList FROM Sto s WHERE s.id = :stoId")
    List<String> findLooseCannonById(@Param("stoId") Long stoId);
}

