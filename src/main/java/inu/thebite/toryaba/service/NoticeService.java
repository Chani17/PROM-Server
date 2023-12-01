package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.NoticeResponse;

import java.util.List;

public interface NoticeService {
    Notice updateComment(Long studentId, String date, AddCommentRequest addCommentRequest);

    List<String> getNoticeDateList(Long studentId, String year, String month);

    NoticeResponse getNotice(Long studentId, String date);
}
