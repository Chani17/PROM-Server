package inu.thebite.toryaba.service.serviceImpl;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.notice.*;
import inu.thebite.toryaba.repository.DetailRepository;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final StudentRepository studentRepository;
    private final DetailRepository detailRepository;

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
    public boolean createSharePdf(Long studentId, String year, int month, String date, ConvertPdfRequest convertPdfRequest) throws DocumentException, IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

//        Detail detail = detailRepository.findByNoticeId(notice.getId())
//                .orElseThrow(() -> new IllegalStateException("해당하는 세부 알림장이 존재하지 않습니다."));

        String html = createHtml(year, month, date, notice, convertPdfRequest);
        generatePdfFromHtml(html, year, month, date, student.getName());

        return true;
    }

    @Override
    public String createHtml(String year, int month, String date, Notice notice, ConvertPdfRequest convertPdfRequest) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        System.out.println("convertPdfRequest.getPdfDetailResponse() = " + convertPdfRequest.getPdfDetailResponse());
        context.setVariable("today", month + "/" + date);
        context.setVariable("content", notice.getComment());
        context.setVariable("lto", convertPdfRequest.getPdfDetailResponse());


        return templateEngine.process("templates/pdf", context);
    }

    @Override
    public void generatePdfFromHtml(String html, String year, int month, String date, String studentName) throws IOException, DocumentException {
        String outputFolder = "src/main/resources/reports/" + studentName + "/" + year + "-" + month + "-" + date + ".pdf";

        File directory = new File("src/main/resources/reports/" + studentName);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("src/main/resources/static/font/NanumBarunGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
