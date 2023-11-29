package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.entity.Lto;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.entity.Student;
import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.lto.LtoRequest;
import inu.thebite.toryaba.model.lto.UpdateLtoStatusRequest;
import inu.thebite.toryaba.repository.DomainRepository;
import inu.thebite.toryaba.repository.LtoRepository;
import inu.thebite.toryaba.repository.StudentRepository;
import inu.thebite.toryaba.service.LtoService;
import inu.thebite.toryaba.service.PointService;
import inu.thebite.toryaba.service.StoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LtoServiceImpl implements LtoService {

    private final LtoRepository ltoRepository;
    private final DomainRepository domainRepository;
    private final StudentRepository studentRepository;
    private final StoService stoService;
    private final PointService pointService;

    @Override
    @Transactional
    public Lto addLto(Long domainId, Long studentId, LtoRequest ltoRequest) {

        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalStateException("해당 영역이 존재하지 않습니다."));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("해당 학생은 존재하지 않습니다."));

        List<Lto> ltoList = ltoRepository.findAllByDomainId(domain.getId());
        Lto lto = Lto.createLto(ltoList.size() + 1, ltoRequest.getName(), ltoRequest.getContents(), ltoRequest.getGame(), domain, student);
        Lto saveLto = ltoRepository.save(lto);
        return saveLto;
    }

    @Transactional
    @Override
    public Lto updateLtoStatus(Long ltoId, UpdateLtoStatusRequest updateLtoStatusRequest) {
        Lto lto = ltoRepository.findById(ltoId)
                .orElseThrow(() -> new IllegalStateException("해당 LTO가 존재하지 않습니다."));

        lto.updateLtoStatus(updateLtoStatusRequest.getStatus());
        return lto;
    }

    @Transactional
    @Override
    public Lto updateLtoHitStatus(Long ltoId, UpdateLtoStatusRequest updateLtoStatusRequest) {
        Lto lto = ltoRepository.findById(ltoId)
                .orElseThrow(() -> new IllegalStateException("해당 LTO가 존재하지 않습니다."));
        lto.updateLtoHitStatus(updateLtoStatusRequest.getStatus());
        return lto;
    }

    @Transactional
    @Override
    public Lto updateLto(Long ltoId, LtoRequest ltoRequest) {
        Lto lto = ltoRepository.findById(ltoId)
                .orElseThrow(() -> new IllegalStateException("해당 LTO가 존재하지 않습니다."));
        lto.updateLTO(ltoRequest.getName(), ltoRequest.getContents(), ltoRequest.getGame());
        return lto;
    }

    @Override
    public List<Lto> getLtoList() {
        List<Lto> LtoList = ltoRepository.findAll();
        return LtoList;
    }

    @Override
    public List<LtoGraphResponse> getLtoGraph(Long ltoId) {
        Lto lto = ltoRepository.findById(ltoId)
                .orElseThrow(() -> new IllegalStateException("해당 LTO가 존재하지 않습니다."));

        List<Sto> stoList = stoService.getStoListByLtoId(lto.getId());
        List<LtoGraphResponse> result = new ArrayList<>();

        for(Sto sto : stoList) {
            LtoGraphResponse response = pointService.getGraphValue(sto.getId());
            result.add(response);
        }
        return result;
    }

    @Transactional
    @Override
    public void deleteLto(Long ltoId) {
        if (ltoRepository.findById(ltoId).isPresent()) {
            ltoRepository.deleteById(ltoId);
        } else {
            throw new IllegalStateException("해당 LTO가 존재하지 않습니다.");
        }
    }


}