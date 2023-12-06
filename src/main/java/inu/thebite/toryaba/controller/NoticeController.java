package inu.thebite.toryaba.controller;

import com.lowagie.text.DocumentException;
import inu.thebite.toryaba.model.notice.*;
import inu.thebite.toryaba.parseThymeleafTemplate;
import inu.thebite.toryaba.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.util.List;

@RestController
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

    // converter html to pdf
    @PostMapping(value = "/{studentId}")
    public boolean createSharePdf(@PathVariable Long studentId,
                               @RequestParam("year") String year,
                               @RequestParam("month") int month,
                               @RequestParam("date") String date,
                               @RequestBody ConvertPdfRequest convertPdfRequest) throws DocumentException, IOException {
        boolean result = noticeService.createSharePdf(studentId, year, month, date, convertPdfRequest);
        return result;
    }

    @GetMapping(value = "/test")
    public ITextRenderer pdfTest() throws DocumentException, IOException {
        parseThymeleafTemplate parseThymeleafTemplate = new parseThymeleafTemplate();
        String html = parseThymeleafTemplate.createHtml();
        ITextRenderer renderer = parseThymeleafTemplate.generatePdfFromHtml(html);
        return renderer;
    }


}
