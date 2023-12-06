package inu.thebite.toryaba.model.notice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DetailGraphResponse {

    private Long id;

    private String comment;

    private List<Float> results;

    private List<String> dates;

    private Long stoId;

    private Long noticeId;

    public DetailGraphResponse(Long id, String comment, Long stoId, Long noticeId) {
        this.id = id;
        this.comment = comment;
        this.stoId = stoId;
        this.noticeId = noticeId;
    }

    public static DetailGraphResponse response(Long id, String comment, List<Float> results, List<String> dates, Long stoId, Long noticeId) {
        DetailGraphResponse response = new DetailGraphResponse();
        response.id = id;
        response.comment = comment;
        response.results = results;
        response.dates = dates;
        response.stoId = stoId;
        response.noticeId = noticeId;
        return response;
    }
}