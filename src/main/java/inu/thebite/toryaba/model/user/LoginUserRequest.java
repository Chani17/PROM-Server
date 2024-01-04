package inu.thebite.toryaba.model.user;


import lombok.Data;

@Data
public class LoginUserRequest {

    private String id;

    private String password;
}
