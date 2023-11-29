package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_seq")
    private Long id;

    @Column(name = "notice_date")
    private String date;

    @Column(name = "notice_comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_seq")
    private Student student;

    public static Notice createNotice(Student student) {
        Notice notice = new Notice();
        notice.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        notice.comment = "";
        notice.student = student;
        return notice;
    }

    public void addComment(String comment) {
        this.comment = comment;
    }
}
