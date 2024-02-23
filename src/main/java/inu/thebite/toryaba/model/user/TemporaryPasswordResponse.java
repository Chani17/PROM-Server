package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class TemporaryPasswordResponse {

    private String password;

    public static TemporaryPasswordResponse response(String password) {
        TemporaryPasswordResponse response = new TemporaryPasswordResponse();
        response.password = password;
        return response;
    }
}
