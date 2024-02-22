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

    Optional<Member> findByNameAndPhoneAndEmail(String name, String phone, String email);

    Optional<Member> findByIdAndPhoneAndName(String id, String phone, String name);

    @Query(value = "SELECT m.member_id, m.member_name FROM tb_therapist t join tb_member m ON t.member_id = m.member_id WHERE t.center_seq = :centerId AND m.member_approval_yn = 'N'", nativeQuery = true)
    List<Therapist> findByCenterIdAndAuth(@Param("centerId") Long centerId);

    @Query("SELECT t.center.id FROM Therapist t WHERE t.id = :id")
    Long findCenterIdById(@Param("id") String id);
}
