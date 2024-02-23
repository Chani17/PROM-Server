package inu.thebite.toryaba.model.user;

import lombok.Data;

@Data
public class FindMemberIdResponse {

    private String id;

    public static FindMemberIdResponse response(String id) {
        FindMemberIdResponse response = new FindMemberIdResponse();
        response.id = id;
        return response;
    }
}
