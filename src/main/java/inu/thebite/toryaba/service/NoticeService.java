package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.notice.AddCommentRequest;

public interface NoticeService {
    void updateComment(Long studentId, String date, AddCommentRequest addCommentRequest);
}
