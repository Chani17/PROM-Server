package inu.thebite.toryaba.model.todo;

import lombok.Data;

import java.util.List;

@Data
public class RecentTodoWithDateResponse {

    private String date;

    private List<String> sto;

    private String teacher;


    public static RecentTodoWithDateResponse response(String date, List<String> sto, String teacher) {
        RecentTodoWithDateResponse response = new RecentTodoWithDateResponse();
        response.date = date;
        response.sto = sto;
        response.teacher = teacher;
        return response;
    }
}
