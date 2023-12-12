package inu.thebite.toryaba.model.notice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NoticesDatesResponse {

    private String year;

    private int month;

    public NoticesDatesResponse(String year, int month) {
        this.year = year;
        this.month = month;
    }
}
