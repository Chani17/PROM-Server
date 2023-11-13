package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_therapist")
@Getter
public class Therapist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_seq")
    private Long id;

    @Column(name = "therapist_id", length = 20, nullable = false, unique = true)
    private String therapistId;

    @Column(name = "therapist_pw", length = 45, nullable = false)
    private String password;

    @Column(name = "therapist_name", length = 45)
    private String name;

    @Column(name = "therapist_email", length = 45)
    private String email;

    @Column(name = "therapist_phone", length = 11)
    private String phone;

    @Column(name = "therapist_auth", length = 7)
    private MemberStatus auth;

    @Column(name = "therapist_approval_yn", length = 1)
    private String approvalYN;

    @Column(name = "therapist_register_dt")
    private String registerDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "center_seq")
    private Center center;

    public static Therapist createTherapist(String therapistId, String password, String name, String email, String phone, Center center) {
        Therapist therapist = new Therapist();
        therapist.therapistId = therapistId;
        therapist.password = password;
        therapist.name = name;
        therapist.email = email;
        therapist.phone = phone;
        therapist.auth = MemberStatus.LEVEL2;
        therapist.approvalYN = "Y";
        therapist.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        therapist.center = center;
        return therapist;
    }
}
