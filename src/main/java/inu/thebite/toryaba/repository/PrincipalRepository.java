package inu.thebite.toryaba.repository;


import inu.thebite.toryaba.entity.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {

    Optional<Principal> findByMemberId(String id);

}
