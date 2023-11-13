package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Long> {

    Optional<Therapist> findByTherapistId(String id);
}
