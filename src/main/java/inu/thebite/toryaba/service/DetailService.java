package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.notice.AddCommentRequest;

public interface DetailService {
    void addDetail(Long studentId, String date, Long stoId);

    void updateComment(Long studentId, String date, Long stoId, AddCommentRequest addCommentRequest);
}
