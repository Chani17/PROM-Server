package inu.thebite.toryaba.model.sto;

import lombok.Data;

@Data
public class UpdateStoRoundRequest {

    private Float plusRate;

    private Float minusRate;

    private String status;

    private String registrant;
}
