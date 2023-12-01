package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.NoticeResponse;
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
    public Notice updateComment(@PathVariable Long studentId,
                                        @RequestParam("date") String date,
                                        @RequestBody AddCommentRequest addCommentRequest) {

        Notice notice = noticeService.updateComment(studentId, date, addCommentRequest);
        return notice;
    }

    // 년, 월에 대한 Notice List 반환
    @GetMapping(value = "/{studentId}/dateList")
    public List<String> getNoticeDateList(@PathVariable Long studentId,
                                          @RequestParam("year") String year,
                                          @RequestParam("month") String month) {
        List<String> response = noticeService.getNoticeDateList(studentId, year, month);
        return response;
    }

    // 해당 날짜에 대한 Notice 가져오기
    @GetMapping(value = "/{studentId}")
    public NoticeResponse getNotice(@PathVariable Long studentId,
                                  @RequestParam("date") String date) {
        NoticeResponse response = noticeService.getNotice(studentId, date);
        return response;
    }


}
