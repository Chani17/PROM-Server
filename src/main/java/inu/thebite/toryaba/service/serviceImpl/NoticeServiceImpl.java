package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.*;
import inu.thebite.toryaba.model.notice.*;
import inu.thebite.toryaba.repository.*;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    @Value("${spring.cloud.gcp.storage.bucket}")
//    private String bucketName;

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
    public List<NoticeResponse> getMonthlyNotice(Long studentId, String year, int month) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        return noticeRepository.findByStudentIdAndYearAndMonth(student.getId(), year, month);
    }

    @Override
    public AutoCommentResponse getAutoComment(Long studentId, String year, int month, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당 날짜에 알림장이 존재하지 않습니다."));

        List<Long> doneLtoList = detailRepository.findByNoticeIdAndYearAndMonthAndDate(notice.getId(), year, month, date);

        List<String> ltoNameList = new ArrayList<>();
        List<String> ltoStatusList = new ArrayList<>();
        for(Long lto : doneLtoList) {
            Lto findLto = ltoRepository.findById(lto)
                    .orElseThrow(() -> new IllegalStateException("존재하지 않는 LTO입니다."));
            ltoNameList.add(findLto.getName());

            if(findLto.getStatus().equals("완료")) ltoStatusList.add(findLto.getName());
        }

        String comment = "오늘은 " + String.join(", ", ltoNameList) + "를(을) 실시했습니다.\n";
        if(!ltoStatusList.isEmpty()) comment += "그 중에서 " + String.join(", ", ltoStatusList) + "은(는) 준거 도달 했습니다.\n";
        comment += "가정에서도 관련한 교육 부탁드립니다.";

        return AutoCommentResponse.response(comment);
    }

    @Override
    public ConvertPdfRequest showWebView(Long studentId, String year, int month, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        List<Detail> detailList = detailRepository.findByNoticeId(notice.getId());
        List<PdfLtoResponse> pdfLtoResponses = new ArrayList<>();

        for(Detail detail : detailList) {
            List<PdfStoResponse> pdfStoResponses = new ArrayList<>();
            for(Long sto : detail.getStoId()) {
                Sto findSto = stoRepository.findById(sto)
                        .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

                List<Point> points = pointRepository.findAllByStoId(findSto.getId());
                List<String> dates = new ArrayList<>();
                List<Float> plus = new ArrayList<>();
                List<Float> minus = new ArrayList<>();
                for(Point point : points) {
                    dates.add(point.getRegisterDate().substring(2, 10));
                    plus.add(point.getPlusRate());
                    minus.add(point.getMinusRate());
                }
                pdfStoResponses.add(PdfStoResponse.pdfStoResponse(findSto.getName(), dates, plus, minus));
            }
            Lto lto = ltoRepository.findByStoId(detail.getLtoId())
                    .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

            pdfLtoResponses.add(PdfLtoResponse.pdfLtoResponse(lto.getName(), detail.getComment(), pdfStoResponses));
        }
        ConvertPdfRequest response = ConvertPdfRequest.convertPdfRequest(month + "/" + date, notice.getComment(), pdfLtoResponses);
        return response;
    }
}
