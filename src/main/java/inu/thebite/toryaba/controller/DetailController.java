package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/details")
public class DetailController {

    private final DetailService detailService;

    @PostMapping(value = "/{studentId}")
    public ResponseEntity addDetail(@PathVariable Long studentId,
                                    @RequestParam("date") String date,
                                    @RequestParam("stoId") Long stoId) {
        detailService.addDetail(studentId, date, stoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{studentId}")
    public ResponseEntity updateComment(@PathVariable Long studentId,
                                        @RequestParam("date") String date,
                                        @RequestParam("stoId") Long stoId,
                                        @RequestBody AddCommentRequest addCommentRequest) {
        detailService.updateComment(studentId, date, stoId, addCommentRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{studentId}")
    public List<Detail> getDetailList(@PathVariable Long studentId,
                                      @RequestParam("date") String date) {
        List<Detail> detailList = detailService.getDetailList(studentId, date);
        return detailList;
    }

}
