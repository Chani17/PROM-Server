package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PdfLtoResponse {

    private String title;

    private String content;

    private List<PdfStoResponse> stoArray;

    public PdfLtoResponse() {
        this.stoArray = new ArrayList<>();
    }

    public static PdfLtoResponse pdfLtoResponse(String title, String content, List<PdfStoResponse> stoArray) {
        PdfLtoResponse pdfLtoResponse = new PdfLtoResponse();
        pdfLtoResponse.title = title;
        pdfLtoResponse.content = content;
        pdfLtoResponse.stoArray = stoArray;
        return pdfLtoResponse;
    }

}
