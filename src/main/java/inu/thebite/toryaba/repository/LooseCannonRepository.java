package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.LooseCannon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LooseCannonRepository extends JpaRepository<LooseCannon, Long> {
}
