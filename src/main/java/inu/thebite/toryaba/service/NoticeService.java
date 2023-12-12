package inu.thebite.toryaba.service;

import com.lowagie.text.DocumentException;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.model.notice.*;

import java.io.IOException;
import java.util.List;

public interface NoticeService {
    NoticeResponse updateComment(Long studentId, String year, int month, String date, AddCommentRequest addCommentRequest);

    List<DateResponse> getNoticeDateList(Long studentId, String year, int month);

    NoticeResponse getNotice(Long studentId, String year, int month, String date);

    String createSharePdf(Long studentId, String year, int month, String date, ConvertPdfRequest convertPdfRequest) throws DocumentException, IOException;

    String createHtml(String year, int month, String date, Notice notice, ConvertPdfRequest convertPdfRequest);

    String generatePdfFromHtml(String html, String year, int month, String date, String studentName) throws IOException, DocumentException;

    List<NoticesDatesResponse> getNoticeDates(Long studentId);

    String savePdf(String pdf);
}
