package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.notice.AddCommentRequest;

import java.util.List;

public interface NoticeService {
    void updateComment(Long studentId, String date, AddCommentRequest addCommentRequest);

    List<String> getDetailListBySelectedDate(Long studentId, String year, String month);
}
