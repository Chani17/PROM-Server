package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Detail;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.DetailGraphResponse;
import inu.thebite.toryaba.model.notice.DetailObjectResponse;
import inu.thebite.toryaba.model.notice.DetailResponse;
import inu.thebite.toryaba.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/details")
public class DetailController {

    private final DetailService detailService;

    @PostMapping(value = "/{studentId}")
    public ResponseEntity addDetail(@PathVariable Long studentId,
                                    @RequestParam("year") String year,
                                    @RequestParam("month") int month,
                                    @RequestParam("date") String date,
                                    @RequestParam("stoId") Long stoId) {
        detailService.addDetail(studentId, year, month, date, stoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{studentId}")
    public DetailResponse updateComment(@PathVariable Long studentId,
                                        @RequestParam("year") String year,
                                        @RequestParam("month") int month,
                                        @RequestParam("date") String date,
                                        @RequestParam("stoId") Long stoId,
                                        @RequestBody AddCommentRequest addCommentRequest) {
        DetailResponse detail = detailService.updateComment(studentId, year, month, date, stoId, addCommentRequest);
        return detail;
    }

    // 해당 날짜에 대한 Detail 반환
    @GetMapping(value = "/{studentId}")
    public List<DetailObjectResponse> getDetailList(@PathVariable Long studentId,
                                                   @RequestParam("year") String year,
                                                   @RequestParam("month") int month,
                                                   @RequestParam("date") String date) {
        List<DetailObjectResponse> detailList = detailService.getDetailList(studentId, year, month, date);
        return detailList;
    }

}
