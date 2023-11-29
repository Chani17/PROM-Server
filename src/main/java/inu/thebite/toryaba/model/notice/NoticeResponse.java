package inu.thebite.toryaba.model.notice;

import inu.thebite.toryaba.entity.Student;
import lombok.Data;

@Data
public class NoticeResponse {

    private Long id;

    private String date;

    private String comment;

    private Student student;

    public static NoticeResponse response(Long id, String date, String comment, Student student) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.id = id;
        noticeResponse.date = date;
        noticeResponse.comment = comment;
        noticeResponse.student = student;
        return noticeResponse;
    }
}
