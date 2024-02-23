package inu.thebite.toryaba.model.lto;

import lombok.Data;

import java.util.List;

@Data
public class DevelopTypeResponse {

    private List<String> developType;

    public static DevelopTypeResponse response(List<String> developType) {
        DevelopTypeResponse response = new DevelopTypeResponse();
        response.developType = developType;
        return response;
    }
}
