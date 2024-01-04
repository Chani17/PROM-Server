package inu.thebite.toryaba.service.serviceImpl;


import inu.thebite.toryaba.entity.*;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    private final MemberRepository memberRepository;


    @Transactional
    @Override
    public Center addCenter(String userId, CenterRequest centerRequest) {

        Director director = (Director) memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요한 서비스입니다."));

        Center center = Center.createCenter(centerRequest.getName(), director);
        centerRepository.save(center);
        return center;
    }

    @Transactional
    @Override
    public Center updateCenter(String userId, Long centerId, CenterRequest centerRequest) {

        memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요한 서비스입니다."));

        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));
        center.updateCenter(centerRequest.getName());
        return center;
    }

    @Transactional
    @Override
    public boolean deleteCenter(String userId, Long centerId) {

        memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요한 서비스입니다."));

        if(centerRepository.findById(centerId).isPresent()) {
            centerRepository.deleteById(centerId);
            return true;
        }
        throw new IllegalStateException("존재하지 않는 센터입니다.");
    }

    @Override
    public List<Center> getCenterList(String userId) {

        List<Center> centerList = new ArrayList<>();

        if(memberRepository.findById(userId).get().getAuth().equals(MemberStatus.ROLE_THERAPIST)) {
            Long centerId = memberRepository.findByTherapistId(userId);
            Center center = centerRepository.findByCenterId(centerId);
            centerList.add(center);
        } else {
            centerList = centerRepository.findAllByDirector(userId);
        }

        return centerList;
    }
}
