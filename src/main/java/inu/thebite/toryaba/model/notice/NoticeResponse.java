package inu.thebite.toryaba.model.notice;

import inu.thebite.toryaba.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeResponse {

    private Long id;

    private String year;

    private int month;

    private String date;

    private String day;

    private String comment;

    private Long studentId;

    public NoticeResponse(Long id, String year, int month, String date, String day, String comment, Long studentId) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.date = date;
        this.day = day;
        this.comment = comment;
        this.studentId = studentId;
    }

    public static NoticeResponse response(Long id, String year, int month, String date, String day, String comment, Long studentId) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.id = id;
        noticeResponse.year = year;
        noticeResponse.month = month;
        noticeResponse.date = date;
        noticeResponse.day = day;
        noticeResponse.comment = comment;
        noticeResponse.studentId = studentId;
        return noticeResponse;
    }
}
