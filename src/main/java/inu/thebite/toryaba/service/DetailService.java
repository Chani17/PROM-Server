package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.DetailGraphResponse;
import inu.thebite.toryaba.model.notice.DetailObjectResponse;
import inu.thebite.toryaba.model.notice.DetailResponse;

import java.util.List;

public interface DetailService {
    void addDetail(Long studentId, String year, int month, String date, Long stoId);

    DetailResponse updateComment(Long studentId, String year, int month, String date, Long stoId, AddCommentRequest addCommentRequest);

    List<DetailObjectResponse> getDetailList(Long studentId, String year, int month, String date);

    String getDetailAutoComment(Long studentId, String year, int month, String date);
}
