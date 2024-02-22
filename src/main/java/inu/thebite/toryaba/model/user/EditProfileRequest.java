package inu.thebite.toryaba.model.user;

import lombok.Data;

import java.util.List;

@Data
public class EditProfileRequest {

    private String forte;

    private List<String> qualification;
}
