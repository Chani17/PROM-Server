package inu.thebite.toryaba.model.notice;

import lombok.Data;

@Data
public class DetailObjectResponse {

    private String comment;

    private DetailGraphResponse detailGraphResponse;

    public static DetailObjectResponse response(String comment, DetailGraphResponse detailGraphResponse) {
        DetailObjectResponse response = new DetailObjectResponse();
        response.comment = comment;
        response.detailGraphResponse = detailGraphResponse;
        return response;
    }
}
