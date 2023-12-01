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
    public Detail addDetail(@PathVariable Long studentId,
                                    @RequestParam("date") String date,
                                    @RequestParam("stoId") Long stoId) {
        Detail detail = detailService.addDetail(studentId, date, stoId);
        return detail;
    }

    @PatchMapping(value = "/{studentId}")
    public Detail updateComment(@PathVariable Long studentId,
                                        @RequestParam("date") String date,
                                        @RequestParam("stoId") Long stoId,
                                        @RequestBody AddCommentRequest addCommentRequest) {
        Detail detail = detailService.updateComment(studentId, date, stoId, addCommentRequest);
        return detail;
    }

    // 해당 날짜에 대한 Detail 반환
    @GetMapping(value = "/{studentId}")
    public List<Detail> getDetailList(@PathVariable Long studentId,
                                      @RequestParam("date") String date) {
        List<Detail> detailList = detailService.getDetailList(studentId, date);
        return detailList;
    }

}
