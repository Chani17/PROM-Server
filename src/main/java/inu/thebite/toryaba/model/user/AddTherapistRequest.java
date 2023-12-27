package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class AddTherapistRequest {

    private String id;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Long centerId;
}
