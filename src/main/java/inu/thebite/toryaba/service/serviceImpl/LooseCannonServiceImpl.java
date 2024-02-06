package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.LooseCannon;
import inu.thebite.toryaba.model.sto.LooseCannonRequest;
import inu.thebite.toryaba.model.sto.LooseCannonResponse;
import inu.thebite.toryaba.repository.LooseCannonRepository;
import inu.thebite.toryaba.service.LooseCannonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LooseCannonServiceImpl implements LooseCannonService {

    private final LooseCannonRepository looseCannonRepository;

    @Transactional
    @Override
    public void addLooseCannon(LooseCannonRequest looseCannonRequest) {
        LooseCannon looseCannon = LooseCannon.createLooseCannon(looseCannonRequest.getContent());
        looseCannonRepository.save(looseCannon);
    }

    @Override
    public LooseCannonResponse getLooseCannonList() {
        List<LooseCannon> result = looseCannonRepository.findAll();

        List<String> looseCannonList = result.stream()
                .map(lc -> lc.getName())
                .collect(Collectors.toList());

        return LooseCannonResponse.createResponse(looseCannonList);
    }
}
