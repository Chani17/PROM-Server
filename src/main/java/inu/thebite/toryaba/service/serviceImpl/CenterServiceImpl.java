package inu.thebite.toryaba.service.serviceImpl;


import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.PrincipalRepository;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    private final PrincipalRepository principalRepository;

    @Transactional
    @Override
    public Center addCenter(String principalId, CenterRequest centerRequest) {

        Principal principal = principalRepository.findByPrincipalId(principalId)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요한 서비스입니다."));

        Center center = Center.createCenter(centerRequest.getName(),principal);
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
    public List<Center> getCenterList() {
        List<Center> centerList = centerRepository.findAll();
        return centerList;
    }
}
