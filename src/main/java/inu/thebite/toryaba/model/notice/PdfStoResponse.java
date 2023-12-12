package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class PdfStoResponse {

    private String name;

    private List<String> date;

    private List<Float> plusRate;

    private List<Float> minusRate;
}
