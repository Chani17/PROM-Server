package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class ConvertPdfRequest {

    private String date;

    private String content;

    private List<PdfLtoResponse> lto;

}
