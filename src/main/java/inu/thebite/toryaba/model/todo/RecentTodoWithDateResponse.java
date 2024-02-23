package inu.thebite.toryaba.model.todo;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RecentTodoWithDateResponse {

    private String date;

    private List<String> sto;

    private List<String> stoStatus;

    private List<String> lto;

    private String teacher;


    public static RecentTodoWithDateResponse response(String date, List<String> sto, List<String> stoStatus, List<String> lto, String teacher) {
        RecentTodoWithDateResponse response = new RecentTodoWithDateResponse();
        response.date = date;
        response.sto = sto;
        response.stoStatus = stoStatus;
        response.lto = lto;
        response.teacher = teacher;
        return response;
    }
}
