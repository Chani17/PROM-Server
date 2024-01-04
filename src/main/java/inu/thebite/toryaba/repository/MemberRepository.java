package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query(value = "SELECT center_seq FROM tb_therapist WHERE member_id = :id", nativeQuery = true)
    Long findByTherapistId(String id);
}
