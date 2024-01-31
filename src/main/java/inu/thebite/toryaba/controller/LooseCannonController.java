package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.looseCannon.LooseCannonRequest;
import inu.thebite.toryaba.service.LooseCannonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
