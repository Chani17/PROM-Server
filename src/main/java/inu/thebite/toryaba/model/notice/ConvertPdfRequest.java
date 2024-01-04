package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConvertPdfRequest {

    private String date;

    private String content;

    private List<PdfLtoResponse> lto;

    public ConvertPdfRequest() {
        this.lto = new ArrayList<>();
    }

    public static ConvertPdfRequest convertPdfRequest(String date, String content, List<PdfLtoResponse> lto) {
        ConvertPdfRequest convertPdfRequest = new ConvertPdfRequest();
        convertPdfRequest.date = date;
        convertPdfRequest.content = content;
        convertPdfRequest.lto = lto;
        return convertPdfRequest;
    }

}
