package inu.thebite.toryaba.repository;


import inu.thebite.toryaba.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

    @Query(value = "SELECT * FROM tb_center WHERE member_id = :id", nativeQuery = true)
    List<Center> findAllByDirector(String id);

    @Query(value = "SELECT * FROM tb_center WHERE center_seq = :id", nativeQuery = true)
    Center findByCenterId(Long id);

}
