package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
