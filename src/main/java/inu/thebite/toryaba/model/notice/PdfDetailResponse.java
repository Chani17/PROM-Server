package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class PdfDetailResponse {

    private String ltoName;

    private String ltoComment;

    private List<String> stoNames;

    // 그래프 값은 어떻게 가져와야하는지 의논 필요함.
}
