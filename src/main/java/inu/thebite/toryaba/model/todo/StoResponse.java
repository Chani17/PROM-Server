package inu.thebite.toryaba.model.todo;

import lombok.Data;

@Data
public class StoResponse {

    private Long stoId;

    private String stoName;

    public static StoResponse stoResponse(Long stoId, String stoName) {
        StoResponse response = new StoResponse();
        response.stoId = stoId;
        response.stoName = stoName;
        return response;
    }
}
