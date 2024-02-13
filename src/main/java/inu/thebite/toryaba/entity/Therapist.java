package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Therapist")
@Table(name = "tb_therapist")
@Getter
public class Therapist extends Member {

    // 전문 분야
    @Column(name = "therapist_forte")
    private String forte;

    // 자격
    @Column(name = "therapist_qualification")
    private String qualification;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "center_seq")
    private Center center;

    public Therapist(String id, String password, String name, String email, String phone, String forte, String qualification, Center center) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.forte = forte;
        this.qualification = qualification;
        this.auth = MemberStatus.ROLE_THERAPIST;
        this.approvalYN = "N";
        this.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.center = center;
    }

    void approveTherapist() {
        this.approvalYN = "N";
    }

}
