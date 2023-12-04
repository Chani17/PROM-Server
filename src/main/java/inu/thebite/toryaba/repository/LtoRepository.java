package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Lto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LtoRepository extends JpaRepository<Lto, Long> {

    List<Lto> findAllByDomainId(Long domainId);

    List<Lto> findAllByStudentId(Long id);
}