package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class PdfLtoResponse {

    private String title;

    private String content;

    private List<PdfStoResponse> stoArray;

}
