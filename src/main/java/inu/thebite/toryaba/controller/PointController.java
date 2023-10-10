package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.point.AddPointRequest;
import inu.thebite.toryaba.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    // add point
    @PostMapping("/{studentId}/sto/{stoId}/point/add")
    public ResponseEntity addPoint(@PathVariable Long stoId,
                                   @PathVariable Long studentId,
                                   @RequestBody AddPointRequest addPointRequest) {
        pointService.addPoint(stoId, studentId, addPointRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // get point list
    @GetMapping("/{stoId}")
}
