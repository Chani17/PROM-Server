package inu.thebite.toryaba.model.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindMemberIdRequest {

    @NotEmpty(message = "필수 항목입니다.")
    private String name;

    @NotEmpty(message = "필수 항목입니다.")
    private String phone;

    @NotEmpty(message = "필수 항목입니다.")
    private String email;
}
