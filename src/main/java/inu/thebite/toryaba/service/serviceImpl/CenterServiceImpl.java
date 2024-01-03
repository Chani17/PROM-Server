package inu.thebite.toryaba.service.serviceImpl;


import inu.thebite.toryaba.entity.*;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.model.center.CenterResponseByTherapist;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    private final MemberRepository memberRepository;


    @Transactional
    @Override
    public Center addCenter(String id, CenterRequest centerRequest) {

        Director director = (Director) memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요한 서비스입니다."));

        Center center = Center.createCenter(centerRequest.getName(), director);
        centerRepository.save(center);
        return center;
    }

    @Transactional
    @Override
    public Center updateCenter(Long centerId, CenterRequest centerRequest) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));
        center.updateCenter(centerRequest.getName());
        return center;
    }

    @Transactional
    @Override
    public void deleteCenter(Long centerId) {
        if(!centerRepository.findById(centerId).isPresent()) {
            throw new IllegalStateException("존재하지 않는 센터입니다.");
        } else {
            centerRepository.deleteById(centerId);
        }
    }

    @Override
    public List<Center> getCenterList(String id) {

        List<Center> centerList = new ArrayList<>();

        if(memberRepository.findById(id).get().getAuth().equals(MemberStatus.ROLE_THERAPIST)) {
            Long centerId = memberRepository.findByTherapistId(id);
            Center center = centerRepository.findByCenterId(centerId);
            centerList.add(center);
        } else {
            centerList = centerRepository.findAllByDirector(id);
        }

        return centerList;
    }
}
