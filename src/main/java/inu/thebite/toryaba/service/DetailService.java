package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.model.notice.AddCommentRequest;

import java.util.List;

public interface DetailService {
    void addDetail(Long studentId, String date, Long stoId);

    void updateComment(Long studentId, String date, Long stoId, AddCommentRequest addCommentRequest);

    List<Detail> getDetailList(Long studentId, String date);
}
