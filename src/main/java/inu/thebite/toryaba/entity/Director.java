package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@DiscriminatorValue("Director")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_director")
public class Director extends Member {

    public Director(String id, String password, String name, String email, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.auth = MemberStatus.LEVEL4;
        this.approvalYN = "Y";
        this.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

//    public static Director createDirector(String directorId, String password, String name, String email, String phone) {
//        Director director = new Director();
//        director.directorId = directorId;
//        director.password = password;
//        director.name = name;
//        director.email = email;
//        director.phone = phone;
//        director.auth = MemberStatus.LEVEL4;
//        director.approvalYN = "Y";
//        director.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
//        return director;
//    }
}
