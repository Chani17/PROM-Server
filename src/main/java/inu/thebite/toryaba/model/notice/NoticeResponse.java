package inu.thebite.toryaba.model.notice;

import inu.thebite.toryaba.entity.Student;
import lombok.Data;

@Data
public class NoticeResponse {

    private Long id;

    private String year;

    private String month;
    private String date;

    private String day;

    private String comment;

    private Long studentId;

    public static NoticeResponse response(Long id, String year, String month, String date, String day, String comment, Long studentId) {
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
