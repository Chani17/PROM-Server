package inu.thebite.toryaba.model.notice;

import lombok.Data;

@Data
public class DetailObjectResponse {

    private Long id;

    private String comment;

    private DetailGraphResponse detailGraphResponse;

    public static DetailObjectResponse response(Long id, String comment, DetailGraphResponse detailGraphResponse) {
        DetailObjectResponse response = new DetailObjectResponse();
        response.id = id;
        response.comment = comment;
        response.detailGraphResponse = detailGraphResponse;
        return response;
    }
}
