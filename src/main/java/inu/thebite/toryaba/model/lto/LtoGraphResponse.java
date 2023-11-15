package inu.thebite.toryaba.model.lto;

import lombok.Data;

import java.util.List;

@Data
public class LtoGraphResponse {

    private Long stoId;

    private List<Float> result;

    private List<String> date;

    public static LtoGraphResponse response(Long stoId, List<Float> result, List<String> date) {
        LtoGraphResponse response = new LtoGraphResponse();
        response.stoId = stoId;
        response.result = result;
        response.date = date;
        return response;
    }
}
