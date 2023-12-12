package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.DetailGraphResponse;
import inu.thebite.toryaba.model.notice.DetailResponse;
import inu.thebite.toryaba.repository.DetailRepository;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.DetailService;
import inu.thebite.toryaba.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;
    private final StudentRepository studentRepository;
    private final StoRepository stoRepository;
    private final NoticeRepository noticeRepository;
    private final PointService pointService;

    @Transactional
    @Override
    public void addDetail(Long studentId, String year, int month, String date, Long stoId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 Notice가 존재하지 않습니다."));

        if(!detailRepository.existsByStoIdAndYearAndMonthAndDate(sto.getId(), year, month, date)) {
            Detail detail = Detail.createDetail(sto.getId(), year, month, date, notice);
            detailRepository.save(detail);
        }
    }

    @Transactional
    @Override
    public DetailResponse updateComment(Long studentId, String year, int month, String date, Long stoId, AddCommentRequest addCommentRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 Notice가 존재하지 않습니다."));

        Detail detail = detailRepository.findByNoticeIdAndStoId(notice.getId(), sto.getId())
                .orElseThrow(() -> new IllegalStateException("해당하는 Detail이 존재하지 않습니다."));

        detail.addComment(addCommentRequest.getComment());

        DetailResponse response = DetailResponse.response(detail.getId(), detail.getComment(), sto.getLto().getId(), sto.getId(), notice.getId());
        return response;
    }

    @Override
    public List<DetailGraphResponse> getDetailList(Long studentId, String year, int month, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentIdAndYearAndMonthAndDate(student.getId(), year, month, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 Notice가 존재하지 않습니다."));

        List<DetailGraphResponse> results = detailRepository.findAllByNoticeId(notice.getId());

        for (DetailGraphResponse detailGraphResponse: results) {
            Sto sto = stoRepository.findById(detailGraphResponse.getStoId())
                    .orElseThrow(() -> new IllegalStateException("존재하지 않는 STO 입니다."));

            LtoGraphResponse graphValue = pointService.getGraphValue(sto.getId());
            detailGraphResponse.setResults(graphValue.getResult());
            detailGraphResponse.setDates(graphValue.getDate());
            detailGraphResponse.setLtoId(sto.getLto().getId());
        }
        return results;
    }
}
