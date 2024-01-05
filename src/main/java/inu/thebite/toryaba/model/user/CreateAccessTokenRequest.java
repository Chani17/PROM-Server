package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class CreateAccessTokenRequest {

    private String refreshToken;
}
