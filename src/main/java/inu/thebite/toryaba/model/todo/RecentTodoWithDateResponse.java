package inu.thebite.toryaba.model.todo;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RecentTodoWithDateResponse {

    private LocalDate date;

    private List<String> sto;

    private String teacher;


    public static RecentTodoWithDateResponse response(LocalDate date, List<String> sto, String teacher) {
        RecentTodoWithDateResponse response = new RecentTodoWithDateResponse();
        response.date = date;
        response.sto = sto;
        response.teacher = teacher;
        return response;
    }
}
