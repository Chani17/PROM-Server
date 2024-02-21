package inu.thebite.toryaba.model.notice;

import lombok.Data;

@Data
public class AutoCommentResponse {

    private String comment;

    public static AutoCommentResponse response(String comment) {
        AutoCommentResponse response = new AutoCommentResponse();
        response.comment = comment;
        return response;
    }
}
