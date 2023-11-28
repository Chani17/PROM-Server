package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.repository.DetailRepository;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.DetailService;
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

    @Transactional
    @Override
    public void addDetail(Long studentId, String date, Long stoId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentAndDate(studentId, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 Notice가 존재하지 않습니다."));

        Detail detail = Detail.createDetail(sto.getId(), notice);
        detailRepository.save(detail);
    }

    @Transactional
    @Override
    public void updateComment(Long studentId, String date, Long stoId, AddCommentRequest addCommentRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentAndDate(studentId, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 Notice가 존재하지 않습니다."));

        Detail detail = detailRepository.findByNotice(notice.getId())
                .orElseThrow(() -> new IllegalStateException("해당하는 Detail이 존재하지 않습니다."));

        detail.addComment(addCommentRequest.getComment());
    }

    @Override
    public List<Detail> getDetailList(Long studentId, String date) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentAndDate(studentId, date)
                .orElseThrow(() -> new IllegalStateException("해당하는 Notice가 존재하지 않습니다."));

        List<Detail> results = detailRepository.findAllByNotice(notice.getId());
        return results;
    }
}
