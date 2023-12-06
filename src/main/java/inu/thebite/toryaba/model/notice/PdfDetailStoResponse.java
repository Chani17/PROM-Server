package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class PdfDetailStoResponse {

    private String stoName;

    private List<String> dates;

    private List<Float> plusRate;

    private List<Float> minusRate;

}
