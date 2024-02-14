package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query(value = "SELECT center_seq FROM tb_therapist WHERE member_id = :id", nativeQuery = true)
    Long findByTherapistId(String id);

    Optional<String> findByNameAndPhoneAndEmail(String name, String phone, String email);

    Optional<Member> findByIdAndPhoneAndEmail(String id, String phone, String name);

    @Query(value = "SELECT member_id, member_name, center_seq FROM tb_therapist WHERE center_seq = : centerId AND member_approval_yn = : 'N'", nativeQuery = true)
    List<Therapist> findByCenterIdAndAuth(@Param("centerId") Long centerId);
}
