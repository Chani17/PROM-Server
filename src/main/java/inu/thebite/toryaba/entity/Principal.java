package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_member")
public class Principal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long id;

    @Column(name = "member_id", length = 20, nullable = false, unique = true)
    private String memberId;

    @Column(name = "member_pw", length = 45, nullable = false)
    private String password;

    @Column(name = "member_name", length = 45)
    private String name;

    @Column(name = "member_email", length = 45)
    private String email;

    @Column(name = "member_phone", length = 11)
    private String phone;

    @Column(name = "member_auth", length = 7)
    private MemberStatus auth;

    @Column(name = "member_approval_yn", length = 1)
    private String approvalYN;

    @Column(name = "member_register_dt")
    private String registerDate;


    public static Principal createPrincipal(String memberId, String password, String name, String email, String phone) {
        Principal principal = new Principal();
        principal.memberId = memberId;
        principal.password = password;
        principal.name = name;
        principal.email = email;
        principal.phone = phone;
        principal.auth = MemberStatus.LEVEL4;
        principal.approvalYN = "Y";
        principal.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        return principal;
    }
}
