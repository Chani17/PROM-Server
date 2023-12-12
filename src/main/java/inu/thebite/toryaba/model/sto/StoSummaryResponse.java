package inu.thebite.toryaba.model.sto;

import inu.thebite.toryaba.entity.Lto;
import inu.thebite.toryaba.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class StoSummaryResponse {

    private Long id;

    private String date;

    private List<Long> stoList;

    private Student student;

    public static StoSummaryResponse response(Long id, String date, List<Long> stoList, Student student) {
        StoSummaryResponse response = new StoSummaryResponse();
        response.id = id;
        response.date = date;
        response.stoList = stoList;
        response.student = student;
        return response;
    }
}
