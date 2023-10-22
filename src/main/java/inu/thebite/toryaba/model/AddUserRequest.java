package inu.thebite.toryaba.model;

import lombok.Data;

@Data
public class AddUserRequest {

    private String id;

    private String password;

    private String name;

    private String email;

    private String phone;
}
