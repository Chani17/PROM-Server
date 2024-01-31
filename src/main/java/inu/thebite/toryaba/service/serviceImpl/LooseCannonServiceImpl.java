package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.LooseCannon;
import inu.thebite.toryaba.model.looseCannon.LooseCannonRequest;
import inu.thebite.toryaba.repository.LooseCannonRepository;
import inu.thebite.toryaba.service.LooseCannonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LooseCannonServiceImpl implements LooseCannonService {

    private final LooseCannonRepository looseCannonRepository;

    @Override
    public void addLooseCannon(LooseCannonRequest looseCannonRequest) {
        LooseCannon looseCannon = LooseCannon.createLooseCannon(looseCannonRequest.getName());
        looseCannonRepository.save(looseCannon);
    }
}
