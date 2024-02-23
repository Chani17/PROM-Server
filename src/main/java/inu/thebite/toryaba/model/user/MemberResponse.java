package inu.thebite.toryaba.model.user;

import lombok.Data;

import java.util.List;

@Data
public class MemberResponse {

    private String name;

    private String forte;

    private List<String> qualification;

    private String centerName;

    public static MemberResponse response(String name, String forte, List<String> qualification, String centerName) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.name = name;
        memberResponse.forte = forte;
        memberResponse.qualification = qualification;
        memberResponse.centerName = centerName;
        return memberResponse;
    }
}
