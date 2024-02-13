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

    public Therapist(String id, String password, String name, String email, String phone, String forte, List<String> qualification, Center center) {
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
