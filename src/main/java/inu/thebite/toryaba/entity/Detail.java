package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_detail")
public class Detail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_seq")
    private Long id;

    @Column(name = "detail_comment")
    private String comment;

    @Column(name = "detail_ltoNum")
    private Long ltoId;

    @ElementCollection
    @Column(name = "detail_stoNum")
    private Set<Long> stoId = new HashSet<>();

    @Column(name = "detail_year")
    private String year;

    @Column(name = "detail_month")
    private int month;

    @Column(name = "detail_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_seq")
    private Notice notice;

    public static Detail createDetail(Long ltoId, Long stoId, String year, int month, String date, Notice notice) {
        Detail detail = new Detail();
        detail.comment = "";
        detail.year = year;
        detail.month = month;
        detail.date = date;
        detail.ltoId = ltoId;
        detail.stoId.add(stoId);
        detail.notice = notice;
        return detail;
    }

    public void updateStoId(Long stoId) {
        this.stoId.add(stoId);
    }

    public void addComment(String comment) {
        this.comment = comment;
    }
}
