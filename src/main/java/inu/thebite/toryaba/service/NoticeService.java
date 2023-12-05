package inu.thebite.toryaba.service;

import com.lowagie.text.DocumentException;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.ConvertPdfRequest;
import inu.thebite.toryaba.model.notice.NoticeResponse;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.util.List;

public interface NoticeService {
    Notice updateComment(Long studentId, String year, String month, String date, AddCommentRequest addCommentRequest);

    List<String> getNoticeDateList(Long studentId, String year, String month);

    NoticeResponse getNotice(Long studentId, String year, String month, String date);

    boolean createSharePdf(Long studentId, String year, String month, String date, ConvertPdfRequest convertPdfRequest) throws DocumentException, IOException;

    String createHtml(String year, String month, String date, Notice notice, ConvertPdfRequest convertPdfRequest);

    void generatePdfFromHtml(String html, String year, String month, String date, String studentName) throws IOException, DocumentException;

}
