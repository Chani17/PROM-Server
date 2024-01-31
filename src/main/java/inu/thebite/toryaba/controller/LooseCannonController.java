package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.looseCannon.LooseCannonRequest;
import inu.thebite.toryaba.model.looseCannon.LooseCannonResponse;
import inu.thebite.toryaba.service.LooseCannonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/lc")
public class LooseCannonController {

    private final LooseCannonService looseCannonService;

    // 돌발 행동 추가
    @PostMapping()
    public void addLooseCannon(@RequestBody LooseCannonRequest looseCannonRequest) {
        looseCannonService.addLooseCannon(looseCannonRequest);
    }

    // 돌발 행동 리스트 가져오기
    @GetMapping()
    public LooseCannonResponse getLooseCannonList() {
        LooseCannonResponse response = looseCannonService.getLooseCannonList();
        return response;
    }
}
