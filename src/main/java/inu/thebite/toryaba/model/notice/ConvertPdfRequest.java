package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class ConvertPdfRequest {

    private List<PdfDetailResponse> pdfDetailResponse;

}
