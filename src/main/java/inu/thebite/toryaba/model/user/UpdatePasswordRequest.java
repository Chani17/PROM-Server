package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class UpdatePasswordRequest {

    private String beforePassword;

    private String afterPassword;
}
