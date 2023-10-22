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
@Table(name = "tb_principal")
public class Principal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "principal_seq")
    private Long id;

    @Column(name = "principal_id", length = 20, nullable = false, unique = true)
    private String principalId;

    @Column(name = "principal_pw", length = 45, nullable = false)
    private String password;

    @Column(name = "principal_name", length = 45)
    private String name;

    @Column(name = "principal_email", length = 45)
    private String email;

    @Column(name = "principal_phone", length = 11)
    private String phone;

    @Column(name = "principal_auth", length = 7)
    private MemberStatus auth;

    @Column(name = "principal_approval_yn", length = 1)
    private String approvalYN;

    @Column(name = "principal_register_dt")
    private String registerDate;


    public static Principal createPrincipal(String principalId, String password, String name, String email, String phone) {
        Principal principal = new Principal();
        principal.principalId = principalId;
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
