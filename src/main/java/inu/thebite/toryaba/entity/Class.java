package inu.thebite.toryaba.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_class")
public class Class extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_seq", length = 11, nullable = false)
    private Long id;

    @Column(name = "class_name", length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "center_seq")
    private Center center;


    public static Class createClass(String name, Center center) {
        Class newClass = new Class();
        newClass.name = name;
        newClass.center = center;
        return newClass;
    }

    public void updateClass(String name) {
        this.name = name;
    }
}
