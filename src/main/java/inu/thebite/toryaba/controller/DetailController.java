package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/details")
public class DetailController {

    private final DetailService detailService;

    @PostMapping(value = "/{studentId}")
    public ResponseEntity addDetail(@PathVariable Long studentId,
                                    @RequestParam("date") String date,
                                    @RequestParam("stoId") Long stoId) {
        detailService.addDetail(studentId, date, stoId)
    }


}
