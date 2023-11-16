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
@Table(name = "tb_director")
public class Director extends BaseEntity {

    @Id
    @Column(name = "director_id", length = 20, nullable = false, unique = true)
    private String directorId;

    @Column(name = "director_pw", length = 45, nullable = false)
    private String password;

    @Column(name = "director_name", length = 45)
    private String name;

    @Column(name = "director_email", length = 45)
    private String email;

    @Column(name = "director_phone", length = 13)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "director_auth", length = 7)
    private MemberStatus auth;

    @Column(name = "director_approval_yn", length = 1)
    private String approvalYN;

    @Column(name = "director_register_dt")
    private String registerDate;


    public static Director createDirector(String directorId, String password, String name, String email, String phone) {
        Director director = new Director();
        director.directorId = directorId;
        director.password = password;
        director.name = name;
        director.email = email;
        director.phone = phone;
        director.auth = MemberStatus.LEVEL4;
        director.approvalYN = "Y";
        director.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        return director;
    }
}
