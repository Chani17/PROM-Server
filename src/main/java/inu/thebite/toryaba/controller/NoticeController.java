package inu.thebite.toryaba.controller;

import com.lowagie.text.DocumentException;
import inu.thebite.toryaba.entity.Notice;
import inu.thebite.toryaba.model.notice.AddCommentRequest;
import inu.thebite.toryaba.model.notice.ConvertPdfRequest;
import inu.thebite.toryaba.model.notice.NoticeResponse;
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
                                        @RequestParam("month") String month,
                                        @RequestParam("date") String date,
                                        @RequestBody AddCommentRequest addCommentRequest) {

        NoticeResponse notice = noticeService.updateComment(studentId, year, month, date, addCommentRequest);
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
                                  @RequestParam("year") String year,
                                  @RequestParam("month") String month,
                                  @RequestParam("date") String date) {
        NoticeResponse response = noticeService.getNotice(studentId, year, month, date);
        return response;
    }

    // converter html to pdf
    @PostMapping(value = "/{studentId}")
    public boolean createSharePdf(@PathVariable Long studentId,
                               @RequestParam("year") String year,
                               @RequestParam("month") String month,
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
