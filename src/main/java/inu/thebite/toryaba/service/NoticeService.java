package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.notice.*;

import java.util.List;

public interface NoticeService {
    NoticeResponse updateComment(Long studentId, String year, int month, String date, AddCommentRequest addCommentRequest);

    List<DateResponse> getNoticeDateList(Long studentId, String year, int month);

    NoticeResponse getNotice(Long studentId, String year, int month, String date);

    // ConvertPdfRequest showWebView(Long studentId, String year, int month, String date);

    List<NoticesDatesResponse> getNoticeDates(Long studentId);

    List<NoticeResponse> getMonthlyNotice(Long studentId, String year, int month);
}
