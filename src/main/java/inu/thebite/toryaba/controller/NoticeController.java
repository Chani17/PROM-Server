package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.notice.*;
import inu.thebite.toryaba.parseThymeleafTemplate;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @PatchMapping("/{studentId}")
    public NoticeResponse updateComment(@PathVariable Long studentId,
                                        @RequestParam("year") String year,
                                        @RequestParam("month") int month,
                                        @RequestParam("date") String date,
                                        @RequestBody AddCommentRequest addCommentRequest) {

        NoticeResponse notice = noticeService.updateComment(studentId, year, month, date, addCommentRequest);
        return notice;
    }

    // 년, 월에 대한 Notice List 반환
    @GetMapping(value = "/{studentId}/dateList")
    public List<DateResponse> getNoticeDateList(@PathVariable Long studentId,
                                                @RequestParam("year") String year,
                                                @RequestParam("month") int month) {
        List<DateResponse> response = noticeService.getNoticeDateList(studentId, year, month);
        return response;
    }

    // 해당 날짜에 대한 Notice 가져오기
    @GetMapping(value = "/{studentId}")
    public NoticeResponse getNotice(@PathVariable Long studentId,
                                  @RequestParam("year") String year,
                                  @RequestParam("month") int month,
                                  @RequestParam("date") String date) {
        NoticeResponse response = noticeService.getNotice(studentId, year, month, date);
        return response;
    }

    @GetMapping(value = "/{studentId}/dates")
    public List<NoticesDatesResponse> getNoticeDates(@PathVariable Long studentId) {
        List<NoticesDatesResponse> response = noticeService.getNoticeDates(studentId);
        return response;
    }

    @GetMapping(value = "/{studentId}/reports")
    public String showWebView(@PathVariable Long studentId,
                              @RequestParam("year") String year,
                              @RequestParam("month") int month,
                              @RequestParam("date") String date,
                              Model model) {
        ConvertPdfRequest response = noticeService.showWebView(studentId, year, month, date);
        model.addAttribute("date", response.getDate());
        model.addAttribute("content", response.getContent());
        model.addAttribute("lto", response.getLto());

        return "report/webView";
    }

}
