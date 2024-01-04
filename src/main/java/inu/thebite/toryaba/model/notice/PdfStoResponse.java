package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class PdfStoResponse {

    private String name;

    private List<String> date;

    private List<Float> plusRate;

    private List<Float> minusRate;


    public static PdfStoResponse pdfStoResponse(String name, List<String> date, List<Float> plusRate, List<Float> minusRate) {
        PdfStoResponse pdfStoResponse = new PdfStoResponse();
        pdfStoResponse.name = name;
        pdfStoResponse.date = date;
        pdfStoResponse.plusRate = plusRate;
        pdfStoResponse.minusRate = minusRate;
        return pdfStoResponse;
    }
}
