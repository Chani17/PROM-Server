package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @PatchMapping("/{studentId}")
    public ResponseEntity updateComment(@PathVariable Long studentId,
                                        @RequestParam("date") String date,
                                        @RequestBody AddCommentRequest addCommentRequest) {

        noticeService.updateComment(studentId, date, addCommentRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 년, 월에 대한 Detail List 반환
    @GetMapping(value = "/{studentId}")
    public List<String> getDetailListBySelectedDate(@PathVariable Long studentId,
                                                    @RequestParam("year") String year,
                                                    @RequestParam("month") String month) {
        List<String> response = noticeService.getDetailListBySelectedDate(studentId, year, month);
        return response;
    }


}
