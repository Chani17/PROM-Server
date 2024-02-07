package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.teachingBoard.GraphByAreaResponse;
import inu.thebite.toryaba.repository.DomainRepository;
import inu.thebite.toryaba.repository.LtoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.TeachingBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeachingBoardServiceImpl implements TeachingBoardService {

    private final StudentRepository studentRepository;
    private final DomainRepository domainRepository;
    private final LtoRepository ltoRepository;


    @Override
    public GraphByAreaResponse getGraphByArea(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생입니다."));
    }
}
