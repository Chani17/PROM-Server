package inu.thebite.toryaba.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
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
import org.springframework.beans.factory.annotation.Value;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final StudentRepository studentRepository;
    private final DetailRepository detailRepository;

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
    public String createSharePdf(Long studentId, String year, int month, String date, ConvertPdfRequest convertPdfRequest) throws DocumentException, IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

//        detailRepository.findByNoticeId(notice.getId())
//                .orElseThrow(() -> new IllegalStateException("해당하는 세부 알림장이 존재하지 않습니다."));

        String html = createHtml(year, month, date, notice, convertPdfRequest);
        String pdf = generatePdfFromHtml(html, year, month, date, student.getName());
        String pdfUrl = savePdf(pdf);

        return pdfUrl;
    }

    @Override
    public String createHtml(String year, int month, String date, Notice notice, ConvertPdfRequest convertPdfRequest) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("date", month + "/" + date);
        context.setVariable("content", notice.getComment());
        context.setVariable("lto", convertPdfRequest.getLto());

        return templateEngine.process("templates/htmltopdf", context);
    }

    @Override
    public String generatePdfFromHtml(String html, String year, int month, String date, String studentName) throws IOException, DocumentException {
        String outputFolder = "report/" + studentName + "/" + year + "-" + month + "-" + date + ".pdf";

        File directory = new File("report/" + studentName);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("src/main/resources/static/font/NanumBarunGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        System.out.println("완료");

        outputStream.close();
        return outputFolder;
    }

    @Override
    public String savePdf(String pdf) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        String contentType = "application/pdf";

        // save PDF
        try {
            byte[] pdfBytes = Files.readAllBytes(Paths.get(pdf));
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, pdf).setContentType(contentType).build();
            Blob uploadPdf = storage.create(blobInfo, pdfBytes);
            String pdfUrl = uploadPdf.getMediaLink();
            return pdfUrl;

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

}
