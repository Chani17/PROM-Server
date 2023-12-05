package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.ConvertPdfRequest;
import inu.thebite.toryaba.model.notice.NoticeResponse;
import inu.thebite.toryaba.repository.DetailRepository;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final StudentRepository studentRepository;
    private final DetailRepository detailRepository;

    @Transactional
    @Override
    public Notice updateComment(Long studentId, String date, AddCommentRequest addCommentRequest) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndDate(student.getId(), date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        notice.addComment(addCommentRequest.getComment());
        return notice;
    }

    @Override
    public List<String> getNoticeDateList(Long studentId, String year, String month) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        List<String> noticeDateList = noticeRepository.findByStudentIdAndDate(student.getId(), year, month);
        return noticeDateList;
    }

    @Override
    public NoticeResponse getNotice(Long studentId, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndDate(student.getId(), date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        NoticeResponse response = NoticeResponse.response(notice.getId(), notice.getDate(), notice.getComment(), notice.getStudent());

        return response;
    }

    @Override
    public void createSharePdf(Long studentId, String date, ConvertPdfRequest convertPdfRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndDate(student.getId(), date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        Detail detail = detailRepository.findByNoticeId(notice.getId())
                .orElseThrow(() -> new IllegalStateException("해당하는 세부 알림장이 존재하지 않습니다."));
    }

    public String createHtml(String date, Notice notice, ConvertPdfRequest convertPdfRequest) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("today", date);
        context.setVariable("content", notice.getComment());
        context.setVariable("LtoContentTitle", convertPdfRequest.getLtoName());
        context.setVariable("LtoContent", convertPdfRequest.getLtoContent());
        context.setVariable("dates", convertPdfRequest.getDates());


        return templateEngine.process("templates/thymeleaf_template", context);
    }
}
