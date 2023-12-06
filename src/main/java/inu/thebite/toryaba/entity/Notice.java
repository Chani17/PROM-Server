package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_seq")
    private Long id;

    @Column(name = "notice_year")
    private String year;

    @Column(name = "notice_month")
    private int month;

    @Column(name = "notice_date")
    private String date;

    @Column(name = "notice_day")
    private String day;

    @Column(name = "notice_comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_seq")
    private Student student;

    public static Notice createNotice(Student student) {
        Notice notice = new Notice();
        notice.year = String.valueOf(LocalDateTime.now().getYear());
        notice.month = LocalDateTime.now().getMonthValue();
        notice.date = String.valueOf(LocalDateTime.now().getDayOfMonth());
        notice.day = String.valueOf(LocalDateTime.now().getDayOfWeek());
        notice.comment = "";
        notice.student = student;
        return notice;
    }

    public void addComment(String comment) {
        this.comment = comment;
    }
}
