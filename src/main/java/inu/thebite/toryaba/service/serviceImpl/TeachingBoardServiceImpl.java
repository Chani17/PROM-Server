package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.teachingBoard.GraphByAreaResponse;
import inu.thebite.toryaba.repository.DomainRepository;
import inu.thebite.toryaba.repository.LtoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.TeachingBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachingBoardServiceImpl implements TeachingBoardService {

    private final StudentRepository studentRepository;
    private final DomainRepository domainRepository;
    private final LtoRepository ltoRepository;


    @Override
    public List<GraphByAreaResponse> getGraphByArea(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 학생입니다."));

        List<Domain> domainList = domainRepository.findAllByCenterId(student.getToryClass().getCenter().getId());
        List<GraphByAreaResponse> graphByAreaResponseList = new ArrayList<>();
        GraphByAreaResponse response = new GraphByAreaResponse();
        List<Integer> count = new ArrayList<>();

        for(Domain domain : domainList) {
            System.out.println("DomainID = " + domain.getId());
            Integer nowCount = ltoRepository.getNowCountByStudentIdAndDomainId(student.getId(), domain.getId(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")));
            Integer before3Count = ltoRepository.getBefore3CountByStudentIdAndDomainId(student.getId(), domain.getId(), LocalDate.now().minusMonths(3));
            count.add(before3Count, nowCount);

            response.setDomainName(domain.getName());
            response.setCount(count);
            graphByAreaResponseList.add(response);
        }

        return graphByAreaResponseList;
    }
}
