package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Therapist")
@Table(name = "tb_therapist")
@Getter
public class Therapist extends Member {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "center_seq")
    private Center center;

    public Therapist(String id, String password, String name, String email, String phone, Center center) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.forte = "";
        this.auth = MemberStatus.ROLE_THERAPIST;
        this.approvalYN = "Y";
        this.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.center = center;
    }

    public void approveTherapist() {
        this.approvalYN = "Y";
    }

}
