package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "detail_stoNum")
    private Long stoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_seq")
    private Notice notice;

    public static Detail createDetail(Long stoId, Notice notice) {
        Detail detail = new Detail();
        detail.comment = "";
        detail.stoId = stoId;
        detail.notice = notice;
        return detail;
    }

    public void addComment(String comment) {
        this.comment = comment;
    }
}
