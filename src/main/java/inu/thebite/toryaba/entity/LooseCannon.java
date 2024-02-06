package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_lc")
@Getter
public class LooseCannon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lc_seq")
    private Long id;

    @Column(name = "lc_name")
    private String name;

    public static LooseCannon createLooseCannon(String name) {
        LooseCannon looseCannon = new LooseCannon();
        looseCannon.name = name;
        return looseCannon;
    }
}
