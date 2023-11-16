package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_center")
public class Center extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_seq", length = 11, nullable = false)
    private Long id;

    @Column(name = "center_name", length = 45, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    private Director director;

    public static Center createCenter(String name, Director director) {
        Center center = new Center();
        center.name = name;
        center.director = director;
        return center;
    }

    public void updateCenter(String name) {
        this.name = name;
    }
}
