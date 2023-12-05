package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.DetailResponse;

import java.util.List;

public interface DetailService {
    Detail addDetail(Long studentId, String year, String month, String date, Long stoId);

    DetailResponse updateComment(Long studentId, String year, String month, String date, Long stoId, AddCommentRequest addCommentRequest);

    List<DetailResponse> getDetailList(Long studentId, String year, String month, String date);
}
