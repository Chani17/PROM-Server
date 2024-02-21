package inu.thebite.toryaba.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_domain")
public class Domain extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_seq", length = 11, nullable = false)
    private Long id;

    // 템플릿 번호
    @Column(name = "tmpl_seq", length = 11)
    private int templateNumber;

    // 영역 이름
    @Column(name = "domain_name", nullable = false, length = 200)
    private String name;

    // 등록 일자
    @Column(name = "domain_reg_dt", nullable = false)
    private String registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_seq")
    private Center center;

    @JsonBackReference
    @OneToMany(mappedBy = "domain", cascade = CascadeType.REMOVE)
    private List<Lto> ltos = new ArrayList<>();

    public static Domain createDomain(int templateNumber, String name, Center center) {
        Domain domain = new Domain();
        domain.templateNumber = templateNumber;
        domain.name = name;
        domain.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        domain.center = center;
        return domain;
    }

    public void updateDomain(String name) {
        this.name = name;
    }
}