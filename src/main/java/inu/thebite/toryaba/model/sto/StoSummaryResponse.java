package inu.thebite.toryaba.model.sto;

import inu.thebite.toryaba.entity.Lto;
import lombok.Data;

@Data
public class StoSummaryResponse {

    private Long id;

    private String name;

    private String status;

    private Lto lto;

    public static StoSummaryResponse response(Long id, String name, String status, Lto lto) {
        StoSummaryResponse response = new StoSummaryResponse();
        response.id = id;
        response.name = name;
        response.status = status;
        response.lto = lto;
        return response;
    }
}
