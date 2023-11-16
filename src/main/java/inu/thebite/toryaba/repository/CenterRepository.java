package inu.thebite.toryaba.repository;


import inu.thebite.toryaba.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

    Optional<Center> findByName(String name);

    void deleteByName(String name);

    @Query(value = "SELECT * FROM tb_center WHERE director_id = :id", nativeQuery = true)
    List<Center> findAllByDirector(String id);
}
