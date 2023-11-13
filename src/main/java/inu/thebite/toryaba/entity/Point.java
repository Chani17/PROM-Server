package inu.thebite.toryaba.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_point")
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_seq", length = 11, nullable = false)
    private Long id;

    // 포인트 회차
    @Column(name = "point_round", length = 11)
    private int round;

    // 포인트 결과
    @Column(name = "point_rslt_cd", length = 1, nullable = false)
    private String result;

    // 등록자
    @Column(name = "point_reg_mbr_seq", length = 11, nullable = false)
    private String registrant;

    // 등록일자
    @Column(name = "point_reg_dt", nullable = false)
    private String registerDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sto_seq")
    private Sto sto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_seq")
    private Student student;

    public static Point createPoint(int round, String result, String registrant, Sto sto, Student student) {
        Point point = new Point();
        point.round = round;
        point.result = result;
        point.registrant = registrant;
        point.registerDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        point.sto = sto;
        point.student = student;
        return point;
    }
}