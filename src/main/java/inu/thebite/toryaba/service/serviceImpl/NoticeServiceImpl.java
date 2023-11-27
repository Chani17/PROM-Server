package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.repository.NoticeRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final StudentRepository studentRepository;

    @Override
    public void updateComment(Long studentId, String date, AddCommentRequest addCommentRequest) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당하는 학생이 존재하지 않습니다."));

        Notice notice = noticeRepository.findByStudentAndDate(student.getId(), date)
                .orElseThrow(() -> new IllegalStateException("해당하는 알림장이 존재하지 않습니다."));

        notice.addComment(addCommentRequest.getComment());
    }
}
