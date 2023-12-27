package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class AddDirectorRequest {

    private String id;

    private String password;

    private String name;

    private String email;

    private String phone;

}
