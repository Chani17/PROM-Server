package inu.thebite.toryaba.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import inu.thebite.toryaba.entity.*;
import inu.thebite.toryaba.model.notice.*;
import inu.thebite.toryaba.repository.*;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final StudentRepository studentRepository;
    private final DetailRepository detailRepository;
    private final StoRepository stoRepository;
    private final PointRepository pointRepository;
    private final LtoRepository ltoRepository;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Transactional
    @Override
    public NoticeResponse updateComment(Long studentId, String year, int month, String date, AddCommentRequest addCommentRequest) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        notice.addComment(addCommentRequest.getComment());

        NoticeResponse response = NoticeResponse.response(notice.getId(), notice.getYear(), notice.getMonth(), notice.getDate(), notice.getDay(), notice.getComment(), student.getId());
        return response;
    }

    @Override
    public List<DateResponse> getNoticeDateList(Long studentId, String year, int month) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        List<DateResponse> noticeDateList = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month);
        return noticeDateList;
    }

    @Override
    public List<NoticesDatesResponse> getNoticeDates(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        List<NoticesDatesResponse> response = noticeRepository.findYearAndMonthByStudentId(student.getId());
        return response;
    }

    @Override
    public NoticeResponse getNotice(Long studentId, String year, int month, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        NoticeResponse response = NoticeResponse.response(notice.getId(), notice.getYear(), notice.getMonth(), notice.getDate(), notice.getDay(), notice.getComment(), student.getId());
        return response;
    }

    @Override
    public ConvertPdfRequest showWebView(Long studentId, String year, int month, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        List<Detail> detailList = detailRepository.findByNoticeId(notice.getId());

        List<String> dates = new ArrayList<>();
        List<Float> plus = new ArrayList<>();
        List<Float> minus = new ArrayList<>();
        List<PdfStoResponse> pdfStoResponses = new ArrayList<>();
        List<PdfLtoResponse> pdfLtoResponses = new ArrayList<>();

        for(Detail detail : detailList) {
            Sto sto = stoRepository.findById(detail.getStoId())
                    .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));
            List<Point> points = pointRepository.findAllByStoId(sto.getId());
            for(Point point : points) {
                dates.add(point.getRegisterDate().substring(2, 10));
                plus.add(point.getPlusRate());
                minus.add(point.getMinusRate());
            }
            pdfStoResponses.add(PdfStoResponse.pdfStoResponse(sto.getName(), dates, plus, minus));
            Lto lto = ltoRepository.findByStoId(sto.getLto().getId())
                    .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));
            pdfLtoResponses.add(PdfLtoResponse.pdfLtoResponse(lto.getName(), detail.getComment(), pdfStoResponses));
        }

        ConvertPdfRequest response = ConvertPdfRequest.convertPdfRequest(month + "/" + date, notice.getComment(), pdfLtoResponses);
        return response;
    }
}
