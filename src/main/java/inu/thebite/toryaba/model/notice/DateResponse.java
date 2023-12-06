package inu.thebite.toryaba.model.notice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DateResponse {

    private String year;

    private int month;

    private String date;

    private String day;

    public DateResponse(String year, int month, String date, String day) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.day = day;
    }
}
