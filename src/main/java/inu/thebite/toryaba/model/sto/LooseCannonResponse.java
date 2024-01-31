package inu.thebite.toryaba.model.sto;

import lombok.Getter;

import java.util.List;

@Getter
public class LooseCannonResponse {

    private List<String> looseCannonList;

    public static LooseCannonResponse createResponse(List<String> list) {
        LooseCannonResponse response = new LooseCannonResponse();
        response.looseCannonList = list;
        return response;
    }

}
