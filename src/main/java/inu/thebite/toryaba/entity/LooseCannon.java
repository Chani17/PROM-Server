package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LooseCannon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lc_id")
    private Long id;

    @Column(name = "lc_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sto_seq")
    private Sto sto;

    public static LooseCannon createLooseCannon(String name, Sto sto) {
        LooseCannon looseCannon = new LooseCannon();
        looseCannon.name = name;
        looseCannon.sto = sto;
        return looseCannon;
    }
}
