package inu.thebite.toryaba.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AddDirectorRequest {

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 ID 입력은 필수항목입니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "전화번호는 필수항목입니다.")
    private String phone;

    private String forte;

    private List<String> qualification;

}
