package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class FindMemberPasswordRequest {

    private String id;

    private String name;

    private String phone;
}
